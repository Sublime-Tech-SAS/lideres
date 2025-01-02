package co.sublimetech.lideres.authentication.presentation.login

import CustomDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import co.sublimetech.lideres.core.design_system.CustomLoader
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.mmk.kmpauth.google.GoogleButtonUiContainer
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.authentication_error_title
import org.jetbrains.compose.resources.stringResource
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
    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        GoogleAuthProvider.create(
            credentials = GoogleAuthCredentials(
                serverId = "1048696109935-vqkarjlqbh7ujllashtkudfkp662bva3.apps.googleusercontent.com"
            )
        )
        authReady = true
    }


    if (state.isLoggingIn) {
        CustomLoader()
        print("logging in")
    } else if (state.canLogin) {
        onAction(LoginAction.OnLoginSuccess)
    } else {

        if (showErrorDialog || state.error != null) {

            CustomDialog(
                title = stringResource(Res.string.authentication_error_title),
                content = stringResource(state.error ?: Res.string.authentication_error_title),
                onConfirm = {
                    showErrorDialog = false
                    onAction(LoginAction.DismissError)
                },
            )
        }

        if (authReady) {
            Column {
                Spacer(modifier = Modifier.weight(0.7f))
                Row() {
                    Spacer(modifier = Modifier.weight(1f))
                    GoogleButtonUiContainer(
                        onGoogleSignInResult = { googleUser ->
                            val tokenId = googleUser?.idToken
                            val accessToken = googleUser?.accessToken
                            val userEmail = googleUser?.email
                            if (tokenId != null
                            //&& userEmail!!.endsWith("@sublimetech.co")
                            ) {
                                onAction(LoginAction.OnLoginClick(tokenId, accessToken ?: "1234"))
                            } else {
                                showErrorDialog = true
                            }
                        }
                    ) {

                        GoogleSignInButton(
                            text = "Iniciar sesión con Google",
                            onClick = { this.onClick() }
                        )


                    }
                    Spacer(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.weight(0.1f))
            }
        }

    }
}


