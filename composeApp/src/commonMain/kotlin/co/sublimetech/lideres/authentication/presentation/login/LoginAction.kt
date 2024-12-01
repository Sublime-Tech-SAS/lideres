package co.sublimetech.lideres.authentication.presentation.login

sealed interface LoginAction {
    data object OnLoginClick: LoginAction
    data object OnRegisterClick: LoginAction
}