package co.sublimetech.lideres.form.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object FormDatabaseConstructor:RoomDatabaseConstructor<FormDatabase>{
    override fun initialize(): FormDatabase
}