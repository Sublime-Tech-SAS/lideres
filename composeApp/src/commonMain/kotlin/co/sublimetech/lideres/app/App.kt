package co.sublimetech.lideres.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.sublimetech.lideres.authentication.presentation.login.LoginScreen
import co.sublimetech.lideres.authentication.presentation.registration.RegisterScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
          RegisterScreenRoot( onRegisterClick = {}, modifier = Modifier)
        }
    }
}