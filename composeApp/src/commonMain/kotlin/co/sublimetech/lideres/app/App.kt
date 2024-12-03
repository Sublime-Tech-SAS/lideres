package co.sublimetech.lideres.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
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
    val scope = rememberCoroutineScope()
    val auth = remember { Firebase.auth }
    val currentUser = auth.currentUser

    MaterialTheme {
        val state = rememberConnectivityState {
            autoStart = true
        }
        when (state.status) {
            is Connectivity.Status.Connected -> scope.launch {
                viewModel.updateStorage()
            }
            is Connectivity.Status.Disconnected -> println("Disconnected from network")
            else -> {}
        }

        val navController = rememberNavController()
        NavigationRoot(
            navController = navController,
            isLoggedIn = currentUser != null
        )
    }
}
