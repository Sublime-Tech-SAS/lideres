package co.sublimetech.lideres.core.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.sublimetech.lideres.core.design_system.theme.Black
import co.sublimetech.lideres.core.design_system.theme.White
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.futura_md_bt
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlineTextField(
    state: TextFieldState,
    title: String,
    error: String? = null,
    maxLength: Int = 30,
    onlyDigits: Boolean = false,
    onlyLetters: Boolean = false,
    dateFormat: Boolean = false,
    startWithThree:Boolean = false,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        state = state,
        textStyle = LocalTextStyle.current.copy(
            fontFamily = FontFamily(Font(Res.font.futura_md_bt)),
            color = Black
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        cursorBrush = SolidColor(Black),
        inputTransformation = InputTransformation.maxLength(maxLength)
            .then {
                if (onlyDigits) {
                    checkNumbers(this)
                } else if (onlyLetters) {
                    capitalizeText(this)
                } else if (dateFormat) {
                    formatToDate(this)
                } else if( startWithThree){
                    numbersStartingWithThree(this)
                }
            },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        decorator = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = state.text.toString().ifEmpty { " " },
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                label = { if (!error.isNullOrEmpty()) Text(error) else Text(title) },
                placeholder = { if (dateFormat) Text(" DÍA   /   MES   /   AÑO ") },
                container =
                {
                    OutlinedTextFieldDefaults.Container(
                        enabled = true,
                        isError = !error.isNullOrEmpty(),
                        interactionSource = interactionSource,
                        colors = TextFieldDefaults.colors().copy(
                            errorContainerColor =White ,
                            errorLabelColor = Color.Red,
                            focusedContainerColor = White,
                            unfocusedContainerColor = White,
                            cursorColor = Black,
                            focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                            focusedIndicatorColor = MaterialTheme.colorScheme.outline,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                            focusedPlaceholderColor = MaterialTheme.colorScheme.tertiary,
                            unfocusedPlaceholderColor = MaterialTheme.colorScheme.tertiary,
                            focusedTextColor = Black
                        ),
                        shape = RoundedCornerShape(8.dp),
                        focusedBorderThickness = 1.dp,
                        unfocusedBorderThickness = 1.dp
                    )
                }
            )
        }
    )
}

@Composable
fun CustomTextField(state: TextFieldState) {
    var isFocused by remember { mutableStateOf(false) }
    BasicTextField(
        state = state,
        textStyle = LocalTextStyle.current.copy(
            fontFamily = FontFamily(Font(Res.font.futura_md_bt)),
            fontSize = 12.sp,
            color = Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(White)
            .padding(top = 10.dp, bottom = 20.dp)
            .onFocusChanged { isFocused = it.isFocused },
        cursorBrush = SolidColor(Black),
        decorator = { innerTextField ->
            if (state.text.isEmpty()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    innerTextField()
                    repeat(10) {
                        HorizontalDivider(thickness = 1.dp)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            } else {
                innerTextField()
            }
        }
    )
}
