package co.sublimetech.lideres.statistics.presentation

import co.sublimetech.lideres.form.domain.Form

data class StatisticsState(
    val fetchedForms: List<Form> = emptyList(),
)