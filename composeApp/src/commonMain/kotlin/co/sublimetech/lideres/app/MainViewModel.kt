package co.sublimetech.lideres.app

import androidx.lifecycle.ViewModel
import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainViewModel : ViewModel(), KoinComponent {

    private val repository: AuthRepositoryInterface = get()

    suspend fun updateStorage(){
        repository.updateStorage()
    }

}