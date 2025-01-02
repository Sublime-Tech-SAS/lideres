package co.sublimetech.lideres.statistics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.sublimetech.lideres.form.domain.FormRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class StatisticsViewModel : ViewModel(), KoinComponent {

    private val repository: FormRepositoryInterface = get()

    private val _state = MutableStateFlow(StatisticsState())
    val state: StateFlow<StatisticsState> get() = _state

    fun onAction(action: StatisticsAction) {
        when (action) {
            StatisticsAction.OnGetForms -> {
                getForms()
            }

            StatisticsAction.OnFormClick -> {
                //Travel to forms
            }
        }
    }

    private fun getForms() {
        _state.value = _state.value.copy(loading = true)
        viewModelScope.launch {
            repository.getForms()
                .catch { e ->
                    e.printStackTrace()
                    _state.value = _state.value.copy(fetchedForms = emptyList())

                }
                .collect { forms ->
                    _state.value = _state.value.copy(fetchedForms = forms, loading = false)
                }
        }
    }
}
