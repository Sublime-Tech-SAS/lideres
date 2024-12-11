package co.sublimetech.lideres.form.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.sublimetech.lideres.core.domain.Result
import co.sublimetech.lideres.form.domain.Form
import co.sublimetech.lideres.form.domain.FormRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


class FormViewModel : ViewModel(), KoinComponent {

    private val repository: FormRepositoryInterface = get()

    private val _state = MutableStateFlow(FormState())
    val state: StateFlow<FormState> get() = _state


    fun onAction(action: FormAction) {
        when (action) {
            FormAction.OnSaveFormClick -> {
                saveForm()
            }

            FormAction.OnGetFormClick -> {
                getForm()
            }

            FormAction.OnGetFormsClick -> {
                getForms()
            }

            FormAction.OnStatisticsClick -> {
                //travel to statistics
            }
        }
    }

    private fun saveForm() {
        viewModelScope.launch {
            val result = repository.saveForm(
                Form(
                    formId = "2",
                    enrollerUid = "LAseXomKQJfuXQFIgfoRtkA2ciX2",
                    applicantName = "intento3AppleFormulario2"

                )
            )
            when (result) {
                is Result.Error -> {
                    //HANDLE ERRORS, LOG TO SYSTEM, WARN ETC
                }

                is Result.Success -> {
                    _state.value = _state.value.copy()
                    getForms()
                    //LET USER KNOW?
                }
            }
        }
    }

    private fun getForm() {
        viewModelScope.launch {
            val result = repository.getForm("2")

            when (result) {
                is Result.Error -> {
                    //HANDLE ERRORS, LOG TO SYSTEM, WARN ETC
                }

                is Result.Success -> {
                    _state.value = _state.value.copy(fetchedFormId = result.data.applicantName)
                    //LET USER KNOW?
                }
            }
        }
    }


    private fun getForms() {
        viewModelScope.launch {
            repository.getForms()
                .catch { e ->
                    e.printStackTrace()
                    _state.value = _state.value.copy(fetchedForms = emptyList())

                }
                .collect { forms ->
                    _state.value = _state.value.copy(fetchedForms = forms)
                }
        }
    }
}

