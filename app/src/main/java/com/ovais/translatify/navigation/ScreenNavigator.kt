package com.ovais.translatify.navigation

sealed interface ScreenNavigator {
    object None : ScreenNavigator

    data class NextRoute(
        val routeId: String,
        val canPopBackStack: Boolean = false
    ) : ScreenNavigator
}