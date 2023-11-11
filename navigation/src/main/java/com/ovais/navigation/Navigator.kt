package com.ovais.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


object Navigator {

    @Composable
    fun Navigate(
        navController: NavHostController,
        scaffoldPadding: PaddingValues
    ) {
        NavHost(navController = navController, startDestination = "startDestination") {
            /*composable(route = Screens.Splash.routeId) {
                SplashScreenView(navController)
            }
            composable(route = Screens.Login.routeId) {
                LoginScreen(navController = navController)
            }
            composable(Screens.Home.routeId) {
                HomeScreenView(navController = navController, scaffoldPadding)
            }
            composable(Screens.Setting.routeId) {
                SettingsScreen(navController = navController, scaffoldPadding)
            }
            composable(Screens.AddExpense.routeId) {
                AddExpenseScreen(scaffoldPadding = scaffoldPadding, navController = navController)
            }*/
        }
    }
}