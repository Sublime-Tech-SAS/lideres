package co.sublimetech.lideres.form.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FormDto(
    @SerialName("numero_de_formulario")
    val formId: String,
    @SerialName("nombre_del_registrador")
    val enrollerUid: String,
    @SerialName("nombre_del_aplicante")
    val applicantName: String
)
