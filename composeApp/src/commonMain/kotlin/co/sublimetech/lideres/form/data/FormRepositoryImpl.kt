package co.sublimetech.lideres.form.data

import co.sublimetech.lideres.BuildKonfig
import co.sublimetech.lideres.core.domain.DataError
import co.sublimetech.lideres.core.domain.Result
import co.sublimetech.lideres.form.data.database.FormDao
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
                formDao.saveForm(form)

                // Attempt to save remotely
                try {
                    val uid = auth.currentUser?.uid ?: throw Exception("User not authenticated")

                    firestore.collection(BuildKonfig.FIREBASE_STORAGE)
                        .document(uid)
                        .collection("formularios")
                        .document(form.formNumber)
                        .set(form)

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
                val localForm = formDao.getForm(formId)

                // Attempt to fetch remote form
                try {
                    val uid = auth.currentUser?.uid ?: throw Exception("User not authenticated")
                    val docRef = firestore
                        .collection(BuildKonfig.FIREBASE_STORAGE)
                        .document(uid)
                        .collection("formularios")
                        .document(formId)

                    val snapshot = docRef.get().data<Form>()

                    // Compare forms and update remote form if outdated
                    if (localForm != null && localForm != snapshot) {
                        firestore.collection(BuildKonfig.FIREBASE_STORAGE)
                            .document(uid)
                            .collection("formularios")
                            .document(localForm.formNumber)
                            .set(localForm)
                        Result.Success(localForm)

                    } else {
                        //If there was no local form but the is a remote, save it
                        formDao.saveForm(snapshot)
                        Result.Success(snapshot)
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
                formDao.getForms().first()
            }

            // Attempt to fetch remote forms without breaking on failure
            try {
                val uid = auth.currentUser?.uid ?: throw Exception("User not authenticated")
                val remoteFormsSnapshot = withContext(Dispatchers.IO) {
                    firestore.collection(BuildKonfig.FIREBASE_STORAGE)
                        .document(uid)
                        .collection("formularios")
                        .get()
                }

                val remoteForms = remoteFormsSnapshot.documents
                    .map { it.data<Form>() }

                // Compare remote and local forms
                val missingRemoteForms = remoteForms.filter { remoteForm ->
                    remoteForm.formNumber !in localForms.map { it.formNumber }
                }

                val missingLocalForms = localForms.filter { localForm ->
                    localForm.formNumber !in remoteForms.map { it.formNumber }
                }

                // Save new remote forms locally
                for (remote in missingRemoteForms) {
                    withContext(Dispatchers.IO) {
                        formDao.saveForm(remote)
                    }
                }

                // Save new local forms remotely
                for (local in missingLocalForms) {
                    withContext(Dispatchers.IO) {
                        firestore.collection(BuildKonfig.FIREBASE_STORAGE)
                            .document(uid)
                            .collection("formularios")
                            .document(local.formNumber)
                            .set(local)
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
