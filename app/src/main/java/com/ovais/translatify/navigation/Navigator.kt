package com.ovais.translatify.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ovais.translatify.home.presentation.HomeView
import com.ovais.translatify.languages.presentation.LanguagesView
import com.ovais.translatify.options.presentation.OptionsView
import com.ovais.translatify.saved_translations.presentation.SavedTranslationView
import com.ovais.translatify.scan_with_camera.presentation.ScanWithCameraView
import com.ovais.translatify.splash.presentation.SplashView
import com.ovais.translatify.voice_translate.presentation.VoiceTranslateView


object Navigator {

    @Composable
    fun Navigate(
        navController: NavHostController,
        scaffoldPadding: PaddingValues = PaddingValues(0.dp),
        startDestination: String = Screens.Splash.routeId
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(route = Screens.Splash.routeId) {
                SplashView(
                    navController = navController,
                    scaffoldPadding = scaffoldPadding
                )
            }
            composable(Screens.Home.routeId) {
                HomeView(
                    navController = navController,
                    scaffoldPadding = scaffoldPadding
                )
            }
            composable(Screens.VoiceTranslate.routeId) {
                VoiceTranslateView(
                    navController = navController,
                    scaffoldPadding = scaffoldPadding
                )
            }
            composable(Screens.Options.routeId) {
                OptionsView(
                    navController = navController,
                    scaffoldPadding = scaffoldPadding
                )
            }
            composable(Screens.SavedTranslations.routeId) {
                SavedTranslationView(
                    navController = navController,
                    scaffoldPadding = scaffoldPadding
                )
            }
            composable(Screens.ScanWithCamera.routeId) {
                ScanWithCameraView(
                    navController = navController,
                    scaffoldPadding = scaffoldPadding
                )
            }
            composable(Screens.AllLanguages.routeId) {
                LanguagesView(
                    navController = navController,
                    scaffoldPadding = scaffoldPadding
                )
            }
        }
    }
}