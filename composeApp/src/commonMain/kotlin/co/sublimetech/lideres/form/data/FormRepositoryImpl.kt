package co.sublimetech.lideres.form.data

import co.sublimetech.lideres.core.domain.DataError
import co.sublimetech.lideres.core.domain.Result
import co.sublimetech.lideres.form.data.database.FormDao
import co.sublimetech.lideres.form.data.mappers.toForm
import co.sublimetech.lideres.form.data.mappers.toFormDto
import co.sublimetech.lideres.form.data.mappers.toFormEntity
import co.sublimetech.lideres.form.data.network.dto.FormDto
import co.sublimetech.lideres.form.domain.Form
import co.sublimetech.lideres.form.domain.FormRepositoryInterface
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class FormRepositoryImpl(
    private val formDao: FormDao,
) : FormRepositoryInterface {
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    override suspend fun saveForm(form: Form): Result<Boolean, DataError.Local> {
        return withContext(Dispatchers.IO) {
            try {
                // Save form locally
                formDao.saveForm(form.toFormEntity(form.formId))

                // Attempt to save remotely
                try {
                    val uid = auth.currentUser?.uid ?: throw Exception("User not authenticated")

                    firestore.collection("usuarios")
                        .document(uid)
                        .collection("formularios")
                        .document(form.formId)
                        .set(form.toFormDto())

                } catch (remoteException: Exception) {
                    // Log the remote error but do not fail the entire operation
                    remoteException.printStackTrace()
                }

                // If local save was successful, return success regardless of remote status
                Result.Success(true)

            } catch (e: Exception) {
                e.printStackTrace()
                Result.Error(DataError.Local.UNKNOWN)
            }
        }
    }

    override suspend fun getForm(formId: String): Result<Form, DataError.Local> {
        return withContext(Dispatchers.IO) {
            try {
                // Fetch local form
                val localForm = formDao.getForm(formId)?.toForm()

                // Attempt to fetch remote form
                try {
                    val uid = auth.currentUser?.uid ?: throw Exception("User not authenticated")
                    val docRef = firestore
                        .collection("usuarios")
                        .document(uid)
                        .collection("formularios")
                        .document(formId)

                    val snapshot = docRef.get().data<FormDto>()
                    val remoteForm = snapshot.let {
                        FormDto(
                            it.formId,
                            it.enrollerUid,
                            it.applicantName
                        ).toForm()
                    }

                    // Compare forms and update remote form if outdated
                    if (localForm != null && localForm != remoteForm) {
                        firestore.collection("usuarios")
                            .document(uid)
                            .collection("formularios")
                            .document(localForm.formId)
                            .set(localForm.toFormDto())
                        Result.Success(localForm)

                    } else {
                        //If there was no local form but the is a remote, save it
                        formDao.saveForm(remoteForm.toFormEntity(remoteForm.formId))
                        Result.Success(remoteForm)
                    }
                } catch (remoteException: Exception) {
                    // Log the remote error but do not fail the entire operation
                    remoteException.printStackTrace()
                }
                Result.Success(localForm ?: throw Exception("No form found"))
            } catch (e: Exception) {
                e.printStackTrace()
                Result.Error(DataError.Local.UNKNOWN)
            }
        }
    }


    override fun getForms(): Flow<List<Form>> = flow {
        try {
            // Fetch local forms first
            val localForms = withContext(Dispatchers.IO) {
                formDao.getForms().first().map { formEntity -> formEntity.toForm() }
            }

            // Attempt to fetch remote forms without breaking on failure
            try {
                val uid = auth.currentUser?.uid ?: throw Exception("User not authenticated")
                val remoteFormsSnapshot = withContext(Dispatchers.IO) {
                    firestore.collection("usuarios")
                        .document(uid)
                        .collection("formularios")
                        .get()
                }

                val remoteForms = remoteFormsSnapshot.documents
                    .map { it.data<FormDto>() }
                    .map { it.toForm() }

                // Compare remote and local forms
                val missingRemoteForms = remoteForms.filter { remoteForm ->
                    remoteForm.formId !in localForms.map { it.formId }
                }

                val missingLocalForms = localForms.filter { localForm ->
                    localForm.formId !in remoteForms.map { it.formId }
                }

                // Save new remote forms locally
                for (remote in missingRemoteForms) {
                    withContext(Dispatchers.IO) {
                        formDao.saveForm(remote.toFormEntity(uid))
                    }
                }

                // Save new local forms remotely
                for (local in missingLocalForms) {
                    withContext(Dispatchers.IO) {
                        firestore.collection("usuarios")
                            .document(uid)
                            .collection("formularios")
                            .document(local.formId)
                            .set(local.toFormDto())
                    }
                }
                emit(remoteForms + missingLocalForms)

            } catch (e: Exception) {
                e.printStackTrace()
                // Emit local forms in case of an error fetching remote forms
                emit(localForms)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(emptyList())
        }
    }


    override suspend fun deleteForm(formId: String) {
        //Not sure if we would want a user to be able to do this
    }
}
