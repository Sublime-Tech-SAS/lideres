package co.sublimetech.lideres.form.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import co.sublimetech.lideres.core.design_system.DropdownComponent
import io.github.joelkanyi.sain.Sain
import io.github.joelkanyi.sain.SignatureAction
import io.github.joelkanyi.sain.SignatureState
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

        var imageBitmap: ImageBitmap? by remember { mutableStateOf(null) }

        Sain(
            state = SignatureState(),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .border(
                    BorderStroke(
                        width = .5.dp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = RoundedCornerShape(8.dp)
                ),
            onComplete = { signatureBitmap ->
                if (signatureBitmap != null) {
                    imageBitmap = signatureBitmap
                } else {
                    println("Signature is empty")
                }
            },
        ) { action ->
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        imageBitmap = null
                        action(SignatureAction.CLEAR)
                    }) {
                    Text("Clear")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        action(SignatureAction.COMPLETE)
                    }) {
                    Text("Complete")
                }
            }
        }
    }
}
