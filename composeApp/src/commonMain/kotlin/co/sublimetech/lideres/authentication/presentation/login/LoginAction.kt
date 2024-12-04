package co.sublimetech.lideres.authentication.presentation.login

sealed interface LoginAction {
    data class OnLoginClick(val tokenId: String, val accessToken: String) : LoginAction
    data object OnLoginSuccess : LoginAction
}