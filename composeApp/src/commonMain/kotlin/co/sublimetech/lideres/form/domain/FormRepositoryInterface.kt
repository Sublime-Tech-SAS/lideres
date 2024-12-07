package co.sublimetech.lideres.form.domain

import co.sublimetech.lideres.core.domain.DataError
import kotlinx.coroutines.flow.Flow
import co.sublimetech.lideres.core.domain.Result

interface FormRepositoryInterface {
    suspend fun saveForm(form: Form):Result<Boolean, DataError.Local>
    suspend fun getForm(formId: String): Result<Form, DataError.Local>
    fun getForms(): Flow<List<Form>>
    suspend fun deleteForm(formId: String)

}