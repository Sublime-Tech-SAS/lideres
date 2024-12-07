package co.sublimetech.lideres.form.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(private val context: Context) {
    actual fun create(): RoomDatabase.Builder<FormDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(FormDatabase.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}