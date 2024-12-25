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
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.sublimetech.lideres.core.design_system.capitalizeText
import co.sublimetech.lideres.core.design_system.checkNumbers
import co.sublimetech.lideres.core.design_system.formatToDate
import co.sublimetech.lideres.core.design_system.numbersStartingWithThree

@Composable
fun CustomTextField(
    state: TextFieldState,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    hint: String? = null,
    title: String?,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    additionalInfo: String? = null,
    maxLength: Int = Int.MAX_VALUE,
    onlyDigits: Boolean = false,
    onlyLetters: Boolean = false,
    dateFormat: Boolean = false,
    phoneNumber: Boolean = false,
    modifier: Modifier = Modifier,
) {

    var isFocused by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(RectangleShape)
            .border(1.dp, Color.Blue, RectangleShape)
            .padding(8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "header",
                style = TextStyle(color = Color.Gray, fontSize = 14.sp),
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 4.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            BasicTextField(
                state = state,
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),
                lineLimits = TextFieldLineLimits.SingleLine,
                cursorBrush = SolidColor(Color.Gray),
                inputTransformation = InputTransformation.maxLength(maxLength)
                    .then {
                        if (phoneNumber) {
                            numbersStartingWithThree(this)
                        }
                        if (onlyDigits) {
                            checkNumbers(this)
                        } else if (onlyLetters) {
                            capitalizeText(this)
                        } else if (dateFormat) {
                            formatToDate(this)
                        }
                    },
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (isFocused) {
                            Color.Blue.copy(
                                alpha = 0.05f
                            )
                        } else {
                            Color.LightGray
                        }
                    )
                    .border(
                        width = 1.dp,
                        color = if (isFocused) {
                            Color.Blue
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
                                    text = hint ?: "",
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
        onlyDigits = true,
        dateFormat = false,
        onlyLetters = false,
        modifier = Modifier
            .fillMaxWidth()
    )
}

