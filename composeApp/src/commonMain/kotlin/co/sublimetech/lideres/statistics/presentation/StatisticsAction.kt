package co.sublimetech.lideres.statistics.presentation


sealed interface StatisticsAction {
    data object OnFormClick : StatisticsAction
    data object OnGetForms : StatisticsAction
}