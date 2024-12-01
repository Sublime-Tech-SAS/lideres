package co.sublimetech.lideres.authentication.presentation.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreenRoot(
    viewModel: RegisterViewModel = koinViewModel(),
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state
    RegisterScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is RegisterAction.OnLoginClick -> onRegisterClick()
                else -> {}
            }
            viewModel.onAction(action)
        },
        modifier = modifier
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
    modifier: Modifier = Modifier,
) {


    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        BasicTextField(
            state = state.email,
        )

        BasicTextField(
            state = state.password,
        )

        Button(onClick = {
            onAction(RegisterAction.OnRegisterClick)
        }) {
            Text("Register")
        }
    }

}
