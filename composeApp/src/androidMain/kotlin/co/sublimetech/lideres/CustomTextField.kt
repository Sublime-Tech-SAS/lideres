package co.sublimetech.lideres

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.allCaps
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    state: TextFieldState,
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    hint: String,
    title: String?,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    additionalInfo: String? = null,
    maxLength: Int = Int.MAX_VALUE,
    onlyDigits: Boolean = false,
    onlyLetters: Boolean = false,
    dateFormat: Boolean = false,
    allCaps: Boolean = false,
    modifier: Modifier = Modifier,
) {

    var isFocused by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (title != null) {
                Text(
                    text = title,
                    color = Color.LightGray
                )
            }
            if (error != null) {
                Text(
                    text = error,
                    color = Color.Red,
                    fontSize = 12.sp
                )
            } else if (additionalInfo != null) {
                Text(
                    text = additionalInfo,
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            state = state,
            textStyle = LocalTextStyle.current.copy(
                color = Color.White
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(Color.White),
            inputTransformation = InputTransformation.maxLength(maxLength)
                .then {
                    if (onlyDigits) {
                        val currentText = this.toString()
                        val filteredText = currentText.filter { it.isDigit() }
                        if (filteredText != currentText) {
                            replace(0, length, filteredText)
                        }
                    }
                }
                .then {
                    if (onlyLetters) {
                        val currentText = this.toString()
                        val filteredText = currentText.filter { it.isDigit() }
                        if (filteredText != currentText) {
                            replace(0, length, filteredText)
                        }
                    }
                }
                .then {
                   if(dateFormat){
                       createDate(this)
                   }
                }
                .then {
                    if (allCaps) {
                        (InputTransformation.allCaps(Locale.current))
                    }
                },
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isFocused) {
                        Color.Green.copy(
                            alpha = 0.05f
                        )
                    } else {
                        Color.Gray
                    }
                )
                .border(
                    width = 1.dp,
                    color = if (isFocused) {
                        Color.Green
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            decorator = { innerBox ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (startIcon != null) {
                        Icon(
                            imageVector = startIcon,
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if (state.text.isEmpty() && !isFocused) {
                            Text(
                                text = hint,
                                color = Color.LightGray.copy(
                                    alpha = 0.4f
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerBox()
                    }
                    if (endIcon != null) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = endIcon,
                            contentDescription = null,
                            tint = Color.LightGray,
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )
                    }
                }
            }
        )
    }
}

fun createDate(buffer: TextFieldBuffer) {
    val currentText = buffer.asCharSequence().toString()
    val filteredText = currentText.filter { it.isDigit() }

    val formattedText = buildString {
        for (i in filteredText.indices) {
            append(filteredText[i])
            if ((i == 1 || i == 3) && i != filteredText.length - 1) {
                append("/")
            }
        }
    }

    val finalText = if (formattedText.length > 10) {
        formattedText.substring(0, 10)
    } else {
        formattedText
    }

    if (finalText != currentText) {
        buffer.replace(0, buffer.length, finalText)
    }
}



@Preview
@Composable
private fun TextFieldPreview() {
    CustomTextField(
        state = rememberTextFieldState(),
        startIcon = null,
        endIcon = null,
        hint = "example@test.com",
        title = "Email",
        error = null,
        additionalInfo = "Must be a valid email",
        modifier = Modifier
            .fillMaxWidth()
    )
}

