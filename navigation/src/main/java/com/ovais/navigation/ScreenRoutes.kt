package com.ovais.navigation

private const val SCREEN_PREFIX = "screen_"

interface ScreenRoutes {
    val routeId: String
}

sealed class Screens(
    override val routeId: String,
    val title: String
) : ScreenRoutes {

    object Splash : Screens(routeId = buildRoute("splash"), title = "Splash")

    object Login : Screens(routeId = buildRoute("login"), title = "Login")

    object Home : Screens(routeId = buildRoute("home"), title = "Home")

    object AddExpense : Screens(routeId = buildRoute("add_expense"), "Add")

    object Setting : Screens(routeId = buildRoute("settings"), title = "Settings")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(routeId)
            append("/$args")
        }
    }
}

private fun buildRoute(id: String) = buildString {
    append(SCREEN_PREFIX)
    append(id)
}