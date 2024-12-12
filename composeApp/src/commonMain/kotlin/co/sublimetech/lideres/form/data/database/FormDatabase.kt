package co.sublimetech.lideres.form.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.sublimetech.lideres.form.domain.Form

@Database(
    entities = [Form::class],
    version = 1
)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(FormDatabaseConstructor::class)
abstract class FormDatabase: RoomDatabase() {
    abstract val formDao: FormDao

    companion object {
        val DB_NAME = "form.db"
    }
}