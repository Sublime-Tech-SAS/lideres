package co.sublimetech.lideres.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import co.sublimetech.lideres.core.domain.DataError
import co.sublimetech.lideres.core.domain.Result
import kotlinx.coroutines.launch
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.authentication_error
import lideres.composeapp.generated.resources.not_active_error
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class LoginViewModel : ViewModel(), KoinComponent {

    private val repository: AuthRepositoryInterface = get()

    var state by mutableStateOf(LoginState())
        private set


    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLoginClick -> {
                loginUser(action.tokenId, action.accessToken)
            }

            is LoginAction.DismissError -> state = state.copy(error = null)

            LoginAction.OnLoginSuccess -> {}
        }
    }


    private fun loginUser(tokenId: String, accessToken: String) {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = repository.loginUser(
                tokenId = tokenId,
                accessToken = accessToken
            )
            state = state.copy(isLoggingIn = false)

            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        state = state.copy(isLoggingIn = false, error = Res.string.not_active_error)
                    } else {
                        state = state.copy(isLoggingIn = false, error = Res.string.authentication_error)
                    }
                }

                is Result.Success -> {
                    state = state.copy(canLogin = true)
                }
            }
        }
    }


    suspend fun logout() {
        repository.logout()
    }
}
