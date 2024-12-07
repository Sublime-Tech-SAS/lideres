package co.sublimetech.lideres.form.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FormDao {

    @Upsert
    suspend fun saveForm(form: FormEntity)

    @Query ("SELECT * FROM FormEntity WHERE formId = :formId")
    suspend fun getForm(formId:String):FormEntity?

    @Query ("SELECT * FROM FormEntity")
    fun getForms(): Flow<List<FormEntity>>

    @Query ("DELETE FROM FormEntity WHERE formId = :formId")
    suspend fun deleteForm(formId:String)

}
