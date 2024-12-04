package co.sublimetech.lideres.app

data class AppState(
    val isUserValidated: Boolean = false,
    val isValidating: Boolean = true,
    val storageUpdated: Boolean = false
)
