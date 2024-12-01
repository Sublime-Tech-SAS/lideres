package co.sublimetech.lideres.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Auth: Route

    @Serializable
    data object App: Route

    @Serializable
    data object Login: Route

    @Serializable
    data object Register: Route

    @Serializable
    data object Statistics: Route

    @Serializable
    data object Form: Route
}