package co.sublimetech.lideres.authentication.presentation.login

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
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state
    LoginScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is LoginAction.OnRegisterClick -> onRegisterClick()
                else -> {}
            }
            viewModel.onAction(action)
        },
        modifier = modifier
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
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
            onAction(LoginAction.OnLoginClick)
        }) {
            Text("Login")
        }
    }

}

