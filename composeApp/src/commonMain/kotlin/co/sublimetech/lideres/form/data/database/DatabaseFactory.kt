package co.sublimetech.lideres.form.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<FormDatabase>
}