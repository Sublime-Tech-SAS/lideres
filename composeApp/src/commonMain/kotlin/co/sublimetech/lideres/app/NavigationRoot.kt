package co.sublimetech.lideres.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import co.sublimetech.lideres.authentication.presentation.login.LoginScreenRoot
import co.sublimetech.lideres.authentication.presentation.login.LoginViewModel
import co.sublimetech.lideres.authentication.presentation.registration.RegisterScreenRoot
import co.sublimetech.lideres.authentication.presentation.registration.RegisterViewModel
import co.sublimetech.lideres.form.presentation.FormScreenRoot
import co.sublimetech.lideres.form.presentation.FormViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Route.App else Route.Auth
    ) {
        authGraph(navController)
        appGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Route.Auth>(
        startDestination = Route.Login
    ) {
        composable<Route.Login> {
            LoginScreenRoot(
                viewModel = koinViewModel<LoginViewModel>(),
                onRegisterClick = { navController.navigate(Route.Register) })
        }
        composable<Route.Register> {
            RegisterScreenRoot(
                viewModel = koinViewModel<RegisterViewModel>(),
                onLoginClick = { navController.navigate(Route.Login) })
        }
    }
}

private fun NavGraphBuilder.appGraph(navController: NavHostController) {

    navigation<Route.App>(
        startDestination = Route.Form
    ) {
        composable<Route.Form> {
            FormScreenRoot(
                viewModel = koinViewModel<FormViewModel>(),
                onStatisticsClick = { navController.navigate(Route.Statistics) })
        }
    }

}

