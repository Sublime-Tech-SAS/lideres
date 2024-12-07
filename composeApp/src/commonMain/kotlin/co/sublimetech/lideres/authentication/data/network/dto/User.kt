package co.sublimetech.lideres.authentication.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("activo")
    val active: Boolean = false,
)