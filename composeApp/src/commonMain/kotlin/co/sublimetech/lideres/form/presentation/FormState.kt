package co.sublimetech.lideres.form.presentation

import androidx.compose.foundation.text.input.TextFieldState
import co.sublimetech.lideres.form.domain.Form
import org.jetbrains.compose.resources.StringResource


data class FormState(
    val fieldValues: Map<String, TextFieldState> = emptyMap(),
    val loading:Boolean = false,
    val fetchedFormNumber: String = "",
    val fetchedForms: List<Form> = emptyList(),
    val formSaved: Boolean = false,
    val error: StringResource? = null,
)