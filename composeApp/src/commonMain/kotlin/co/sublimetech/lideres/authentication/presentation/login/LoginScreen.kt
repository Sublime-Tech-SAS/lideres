package co.sublimetech.lideres.authentication.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.mmk.kmpauth.google.GoogleButtonUiContainer
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
) {
    val state = viewModel.state
    LoginScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is LoginAction.OnLoginSuccess -> onLoginSuccess()
                else -> {}
            }
            viewModel.onAction(action)
        },
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {

    var authReady by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        GoogleAuthProvider.create(
            credentials = GoogleAuthCredentials(
                serverId = "1048696109935-vqkarjlqbh7ujllashtkudfkp662bva3.apps.googleusercontent.com"
            )
        )
        authReady = true
    }


    if (state.isLoggingIn) {
        //show loader
        print("logging in")
    } else if (state.canLogin) {
        onAction(LoginAction.OnLoginSuccess)
    } else {

        if (authReady) {
            GoogleButtonUiContainer(
                onGoogleSignInResult = { googleUser ->
                    val tokenId = googleUser?.idToken
                    val accessToken = googleUser?.accessToken
                    val userEmail = googleUser?.email
                    if (tokenId != null
                        //&& userEmail!!.endsWith("@sublimetech.co")
                    ) {
                        onAction(LoginAction.OnLoginClick(tokenId, accessToken ?: "1234"))
                    }
                }
            ) {
                GoogleSignInButton(
                    text = "Iniciar sesión con Google",
                    onClick = { this.onClick() }
                )
            }
        }

    }
}


