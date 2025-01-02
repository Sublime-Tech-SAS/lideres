package co.sublimetech.lideres.app

import CustomDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import co.sublimetech.lideres.core.design_system.CustomLoader
import co.sublimetech.lideres.core.design_system.theme.LideresTheme
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.jordond.connectivity.Connectivity
import dev.jordond.connectivity.compose.rememberConnectivityState
import kotlinx.coroutines.launch
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.network_error_title
import lideres.composeapp.generated.resources.no_network_error
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel: MainViewModel = koinViewModel()
    val state = viewModel.state
    val scope = rememberCoroutineScope()
    val auth = remember { Firebase.auth }
    val currentUser = auth.currentUser
    var showErrorDialog by remember { mutableStateOf(false) }

    val connectivityState = rememberConnectivityState {
        autoStart = true
    }
    when (connectivityState.status) {
        is Connectivity.Status.Connected -> scope.launch {
            viewModel.updateStorage()
        }

        is Connectivity.Status.Disconnected -> {
            showErrorDialog = true
            println("Disconnected from network")
        }

        else

            -> {
        }
    }

    LaunchedEffect(Unit) {
        if (currentUser != null) {
            viewModel.validateUser(currentUser.uid)
        }

    }
    LideresTheme {
        if (state.isValidating && currentUser != null) {
            CustomLoader()
            println("Validating user")
        } else {
            if (showErrorDialog) {
                CustomDialog(
                    title = stringResource(Res.string.network_error_title),
                    content = stringResource(Res.string.no_network_error),
                    onConfirm = { showErrorDialog = false },
                )
            }
            val navController = rememberNavController()
            NavigationRoot(
                navController = navController,
                isValidated = state.isUserValidated
            )
        }
    }
}



