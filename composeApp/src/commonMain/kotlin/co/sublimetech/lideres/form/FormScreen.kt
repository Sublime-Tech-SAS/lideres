package co.sublimetech.lideres.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp


@Composable
fun FormScreen() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        BasicTextField(
            state = TextFieldState("Prueba"),
            textStyle = LocalTextStyle.current.copy(
                color = Color.LightGray,
            ),

            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(Color.LightGray),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
        )
        BasicTextField(
            state = TextFieldState("Prueba"),
            textStyle = LocalTextStyle.current.copy(
                color = Color.LightGray,
            ),

            lineLimits = TextFieldLineLimits.SingleLine,
            cursorBrush = SolidColor(Color.LightGray),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
        )
    }
}
