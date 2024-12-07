package co.sublimetech.lideres.form.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FormEntity(
    @PrimaryKey(autoGenerate = false)
    val formId:String,
    val enrollerUid:String,
    val applicantName:String,
)