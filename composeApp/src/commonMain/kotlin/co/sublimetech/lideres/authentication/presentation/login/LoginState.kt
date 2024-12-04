package co.sublimetech.lideres.authentication.presentation.login

import androidx.compose.foundation.text.input.TextFieldState

data class LoginState(
    val tokenId: String = "",
    val accessToken: String = "",
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false
)