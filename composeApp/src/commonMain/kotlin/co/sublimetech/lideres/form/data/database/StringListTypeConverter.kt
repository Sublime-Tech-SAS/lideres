package co.sublimetech.lideres.form.data.database

import androidx.room.TypeConverter
import co.sublimetech.lideres.form.domain.Applicant
import co.sublimetech.lideres.form.domain.ThirdParty
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StringListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun fromApplicant(applicant: Applicant): String {
        return Json.encodeToString(applicant)
    }

    @TypeConverter
    fun toApplicant(value: String): Applicant {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromThirdParty(thirdParty: ThirdParty): String {
        return Json.encodeToString(thirdParty)
    }

    @TypeConverter
    fun toThirdParty(value: String): ThirdParty {
        return Json.decodeFromString(value)
    }
}