package co.sublimetech.lideres.form.presentation

import androidx.compose.foundation.text.input.TextFieldState
import co.sublimetech.lideres.form.domain.Form


data class FormState(
    val fieldValues: Map<String, TextFieldState> = emptyMap(),
    val fetchedFormNumber: String = "",
    val fetchedForms: List<Form> = emptyList(),
    val formSaved: Boolean = false,
)