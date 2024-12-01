package co.sublimetech.lideres.authentication.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import co.sublimetech.lideres.authentication.presentation.login.LoginAction
import co.sublimetech.lideres.core.domain.Result
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class RegisterViewModel: ViewModel(), KoinComponent {

    private val repository: AuthRepositoryInterface = get()

    var state by mutableStateOf(RegisterState())
        private set


    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnRegisterClick -> {
                registerUser()
            }

            RegisterAction.OnLoginClick -> {}
        }
    }


    private fun registerUser() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)

            val result = repository.registerUser(
                state.email.text.toString(),
                state.password.text.toString()
            )
            state = state.copy(isRegistering = false)


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
