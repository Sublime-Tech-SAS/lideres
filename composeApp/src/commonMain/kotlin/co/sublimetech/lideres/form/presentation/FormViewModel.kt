package co.sublimetech.lideres.form.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class FormViewModel : ViewModel(), KoinComponent {

    // private val repository: FormRepositoryInterface = get()

    var state by mutableStateOf(FormState())
        private set


    fun onAction(action: FormAction) {
        when (action) {
            FormAction.OnSaveFormClick -> {}
            FormAction.OnStatisticsClick -> {}
        }
    }

}