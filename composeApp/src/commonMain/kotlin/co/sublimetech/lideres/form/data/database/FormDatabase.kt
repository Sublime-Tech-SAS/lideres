package co.sublimetech.lideres.form.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [FormEntity::class],
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