package co.sublimetech.lideres.form.presentation

import androidx.compose.foundation.text.input.TextFieldState
import co.sublimetech.lideres.form.domain.Form

data class FormState(
    val field1: TextFieldState = TextFieldState(),
    val field2: TextFieldState = TextFieldState(),
    val fetchedFormId: String = "",
    val fetchedForms:List<Form> = emptyList(),
    val formSaved: Boolean = false
)