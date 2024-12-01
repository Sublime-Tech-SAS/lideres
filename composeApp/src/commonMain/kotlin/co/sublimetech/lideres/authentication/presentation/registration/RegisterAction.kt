package co.sublimetech.lideres.authentication.presentation.registration

import co.sublimetech.lideres.authentication.presentation.login.LoginAction

interface RegisterAction {
    data object OnLoginClick: RegisterAction
    data object OnRegisterClick: RegisterAction
}