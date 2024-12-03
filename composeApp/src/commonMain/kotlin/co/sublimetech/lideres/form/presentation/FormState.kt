package co.sublimetech.lideres.form.presentation

import androidx.compose.foundation.text.input.TextFieldState

data class FormState(
    val field1: TextFieldState = TextFieldState(),
    val field2: TextFieldState = TextFieldState(),
    val field3: TextFieldState = TextFieldState(),
)