package co.sublimetech.lideres.core.presentation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform