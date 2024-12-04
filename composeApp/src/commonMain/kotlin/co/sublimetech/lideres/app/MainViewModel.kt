package co.sublimetech.lideres.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import co.sublimetech.lideres.core.domain.DataError
import co.sublimetech.lideres.core.domain.Result
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainViewModel : ViewModel(), KoinComponent {

    private val repository: AuthRepositoryInterface = get()

    var state by mutableStateOf(AppState())
        private set

    fun updateStorage() {
        viewModelScope.launch {
            val result = repository.updateStorage()
            when (result) {
                is Result.Error -> {
                    //HANDLE ERRORS, LOG TO SYSTEM, WARN ETC
                }
                is Result.Success -> {
                    state = state.copy(storageUpdated = true)
                    //LET USER KNOW?
                }
            }
        }
    }

    fun validateUser(userId: String) {
        viewModelScope.launch {
            state = state.copy(isValidating = true)
            val result = repository.validateActiveUser(userId)

            when (result) {
                is Result.Error -> {
                    if (result.error == DataError.Network.UNAUTHORIZED) {
                        //HANDLE UNAUTHORIZED ERROR
                    } else {
                        //HANDLE OTHER ERRORS
                    }
                }

                is Result.Success -> {
                    state = state.copy(isUserValidated = true, isValidating = false)
                }
            }
        }
    }
}