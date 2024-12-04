package co.sublimetech.lideres.authentication.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("activo")
    val active: Boolean = false,
)