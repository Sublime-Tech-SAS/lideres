package co.sublimetech.lideres.form.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import co.sublimetech.lideres.form.domain.Form
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {

    @Upsert
    suspend fun saveForm(form: Form)

    @Query ("SELECT * FROM Form WHERE formNumber = :formNumber")
    suspend fun getForm(formNumber:String):Form?

    @Query ("SELECT * FROM Form")
    fun getForms(): Flow<List<Form>>

    @Query ("DELETE FROM Form WHERE formNumber = :formNumber")
    suspend fun deleteForm(formNumber:String)

}
