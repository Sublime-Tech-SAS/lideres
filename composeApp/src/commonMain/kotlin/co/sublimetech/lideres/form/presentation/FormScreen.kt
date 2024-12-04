package co.sublimetech.lideres.form.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import co.sublimetech.lideres.core.design_system.DropdownComponent
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun FormScreenRoot(
    viewModel: FormViewModel = koinViewModel(),
    onStatisticsClick: () -> Unit,
) {
    val state = viewModel.state
    FormScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is FormAction.OnStatisticsClick -> onStatisticsClick()
                else -> {}
            }
            viewModel.onAction(action)
        },
    )
}


@Composable
fun FormScreen(
    state: FormState,
    onAction: (FormAction) -> Unit,
) {
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
        DropdownComponent()
    }
}
