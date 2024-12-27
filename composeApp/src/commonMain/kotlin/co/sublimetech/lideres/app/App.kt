package co.sublimetech.lideres.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import co.sublimetech.lideres.core.design_system.theme.LideresTheme
import co.sublimetech.lideres.form.presentation.FormScreenRoot
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.jordond.connectivity.Connectivity
import dev.jordond.connectivity.compose.rememberConnectivityState
import kotlinx.coroutines.launch
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

    val connectivityState = rememberConnectivityState {
        autoStart = true
    }
    when (connectivityState.status) {
        is Connectivity.Status.Connected -> scope.launch {
            viewModel.updateStorage()
        }

        is Connectivity.Status.Disconnected -> println("Disconnected from network")
        else -> {}
    }

    LaunchedEffect(Unit) {
        if (currentUser != null) {
            viewModel.validateUser(currentUser.uid)
        }

    }

    if (state.isValidating && currentUser != null) {
       println("Validating user")
    } else
    LideresTheme {
        FormScreenRoot(){}
      //  val navController = rememberNavController()
      //  NavigationRoot(
      //      navController = navController,
      //      isValidated = state.isUserValidated
      //  )
    }
}



