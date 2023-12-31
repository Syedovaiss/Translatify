package com.ovais.translatify.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.ovais.common.storage.LocalStorageManager
import com.ovais.translatify.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val localStorageManager: LocalStorageManager
) : ViewModel() {

    init {
        startNavigation()
    }

    private var navController: NavHostController? = null

    private fun startNavigation() {
        viewModelScope.launch {
            delay(2000)
            /*localStorageManager.isLoggedIn().collectLatest { isLoggedIn ->
                if (isLoggedIn.default()) {
                    navigateToHome()
                } else {
                    navigateToLogin()
                }
            }*/
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        navController?.let { controller ->
            controller.popBackStack()
            controller.navigate(Screens.Home.routeId)
            navController = null
        }

    }

    fun setupNavigation(navController: NavHostController?) {
        this.navController = navController
    }
}