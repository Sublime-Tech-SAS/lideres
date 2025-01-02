package co.sublimetech.lideres.authentication.presentation.login

import org.jetbrains.compose.resources.StringResource

data class LoginState(
    val tokenId: String = "",
    val accessToken: String = "",
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false,
    val error:StringResource? = null
)