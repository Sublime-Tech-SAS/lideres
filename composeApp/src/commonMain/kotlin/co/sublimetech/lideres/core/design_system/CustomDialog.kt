import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomDialog(
    title: String,
    content: String,
    buttonText: String? = null,
    onDismissRequest: (() -> Unit)? = null,
    onConfirm: () -> Unit,
) {
    AlertDialog(

        title = { Text(text = title) },
        text = { Text(text = content) },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(buttonText ?: "Confirmar")
            }
        },
        onDismissRequest = onDismissRequest ?: {},

        dismissButton = {
            if (onDismissRequest != null) {
                Button(
                    onClick = onDismissRequest,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("No")
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}


@Composable
fun CustomDialogPreview() {
    CustomDialog(
        title = "Custom Dialog",
        content = "This is a custom dialog with custom content.",
        onDismissRequest = { /* Handle dismiss */ },
        onConfirm = { /* Handle confirm */ }
    )
}
