package co.sublimetech.lideres.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import co.sublimetech.lideres.core.domain.Result
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class LoginViewModel : ViewModel(), KoinComponent {

    private val repository: AuthRepositoryInterface = get()

    var state by mutableStateOf(LoginState())
        private set


    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnLoginClick -> {
                loginUser()
            }

            LoginAction.OnRegisterClick -> {}
        }
    }


    private fun loginUser() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)

            val result = repository.loginUser(
                state.email.text.toString(),
                state.password.text.toString()
            )
            state = state.copy(isLoggingIn = false)


            when (result) {
                is Result.Error -> {
                    println(result)
                }

                is Result.Success<*> -> {
                    println(result)
                }
            }
        }
    }
}
