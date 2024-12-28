package co.sublimetech.lideres.core.design_system

import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun formatToDate(buffer: TextFieldBuffer) {
    val currentText = buffer.toString()
    val filteredText = currentText.filter { it.isDigit() }

    val formattedText = buildString {
        for (i in filteredText.indices) {
            append(filteredText[i])
            if ((i == 1 || i == 3) && i != filteredText.length - 1) {
                append("/")
            }
        }
    }

    val parts = formattedText.split("/")
    val validParts = mutableListOf<String>()

    if (parts.isNotEmpty()) {
        val day = parts[0].toIntOrNull()
        if (day != null && day in 1..31) {
            validParts.add(parts[0])
        }
    }

    if (parts.size > 1) {
        val month = parts[1].toIntOrNull()
        if (month != null && month in 1..12) {
            validParts.add(parts[1])
        }
    }

    if (parts.size > 2) {
        val year = parts[2]
        if (year.length < 4 || (year.length == 4 && (year.startsWith("19") || year.startsWith("20")))) {
            validParts.add(year)
        } else if (year.length > 4) {
            validParts.add(year.take(4))
        }
    }

    val validFormattedText = validParts.joinToString("/") { it }

    if (validFormattedText != currentText) {
        buffer.replace(0, buffer.length, validFormattedText)
    }
}


fun capitalizeText(buffer: TextFieldBuffer) {
    val currentText = buffer.toString()
    val capitalizedText = buildString {
        var capitalizeNext = true
        for (char in currentText) {
            if (char.isLetter()) {
                if (capitalizeNext) {
                    append(char.uppercaseChar())
                    capitalizeNext = false
                } else {
                    append(char.lowercaseChar())
                }
            } else if (char == ' ') {
                append(char)
                capitalizeNext = true
            }
        }
    }

    if (capitalizedText != currentText) {
        buffer.replace(0, buffer.length, capitalizedText)
    }
}

fun checkNumbers(buffer: TextFieldBuffer) {
    val currentText = buffer.toString()
    val filteredText = currentText.filter { it.isDigit() }
    if (filteredText != currentText) {
        buffer.replace(0, buffer.length, filteredText)
    }
}


fun numbersStartingWithThree(buffer: TextFieldBuffer) {
    val currentText = buffer.asCharSequence().toString()
    val filteredText = currentText.filter { it.isDigit() }

    val validatedText = buildString {
        for (i in filteredText.indices) {
            if (i == 0 && filteredText[i] != '3') {
                continue
            }
            append(filteredText[i])
        }
    }

    if (validatedText != currentText) {
        buffer.replace(0, buffer.length, validatedText)
    }
}

fun isDateBeforeToday(dateString: String): Boolean {
    val parts = dateString.split("/")
    if (parts.size == 3) {
        val day = parts[0].toIntOrNull()
        val month = parts[1].toIntOrNull()
        val year =
            parts[2].toIntOrNull()
        if (day == null || month == null || year == null)
            return false
        return try {
            val inputDate = LocalDate(year, month, day)
            val today = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()).date
            inputDate <= today
        } catch (e: Exception) {
            false
        }
    }
    return true
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return emailRegex.matches(email)
}


@Composable
fun validateEmailField(emailValue: String, setEmailPatternError: (String) -> Unit) {
    LaunchedEffect(emailValue) {
        if (emailValue.isNotBlank()) {
            setEmailPatternError(if (isValidEmail(emailValue)) "" else "Formato incorrecto")
        }
    }
}

@Composable
fun validateDateField(dateValue: String, setDateError: (String) -> Unit) {
    LaunchedEffect(dateValue) {
        setDateError(
            if (isDateBeforeToday(
                    dateValue
                )
            ) {
                ""
            } else {
                "Fecha incorrecta: no puede ser posterior a hoy."
            }
        )
    }
}