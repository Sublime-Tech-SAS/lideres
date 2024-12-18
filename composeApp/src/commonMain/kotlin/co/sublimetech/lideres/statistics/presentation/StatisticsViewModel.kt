package co.sublimetech.lideres.statistics.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

class StatisticsViewModel  : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(StatisticsState())
    val state: StateFlow<StatisticsState> get() = _state

    fun onAction(action: StatisticsAction) {
        when (action) {
            StatisticsAction.OnFormClick -> {

            }
        }
    }
}