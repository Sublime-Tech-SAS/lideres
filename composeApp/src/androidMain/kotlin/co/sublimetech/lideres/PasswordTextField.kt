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
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PasswordTextField(
    state: TextFieldState,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    hint: String,
    title: String?,
    modifier: Modifier = Modifier,
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (title != null) {
                Text(
                    text = title,
                    color = Color.LightGray
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        BasicSecureTextField(
            state = state,
            textObfuscationMode = if (isPasswordVisible) {
                TextObfuscationMode.Visible
            } else TextObfuscationMode.Hidden,
            textStyle = LocalTextStyle.current.copy(
                color = Color.White
            ),
            cursorBrush = SolidColor(Color.White),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isFocused) {
                        Color.Green.copy(
                            alpha = 0.05f
                        )
                    } else {
                        Color.LightGray
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
                .padding(horizontal = 12.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            decorator = { innerBox ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //  Icon(
                    //      imageVector = TODO,
                    //      contentDescription = null,
                    //      tint = Color.LightGray
                    //  )
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if (state.text.isEmpty() && !isFocused) {
                            Text(
                                text = hint,
                                color = Color.Black.copy(
                                    alpha = 0.4f
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerBox()
                    }
                    // IconButton(onClick = onTogglePasswordVisibility) {
                    //     Icon(
                    //         imageVector = if (!isPasswordVisible) {
                    //             //TODO
                    //         } else //TODO,
                    //         contentDescription = if(isPasswordVisible) {
                    //             stringResource(id = TODO)
                    //         } else {
                    //             stringResource(id =TODO)
                    //         },
                    //         tint = Color.LightGray
                    //     )
                    // }
                }
            }
        )
    }
}

@Preview
@Composable
private fun TextFieldPreview() {
    PasswordTextField(
        state = rememberTextFieldState(),
        hint = "EnterPassword",
        title = "Password",
        modifier = Modifier
            .fillMaxWidth(),
        isPasswordVisible = false,
        onTogglePasswordVisibility = {}
    )
}