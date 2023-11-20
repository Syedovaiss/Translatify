package com.ovais.translatify.app.activity.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ovais.translatify.app.theme.TranslatifyDarkTheme
import com.ovais.translatify.app.ui.BottomBar
import com.ovais.translatify.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TranslatifyActivity : ComponentActivity() {
    val activityViewModel: TranslatifyActivityViewModel
        get() = viewModel
    private val viewModel: TranslatifyActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val bottomNavUiData by viewModel.bottomNavUiData.collectAsState()
            TranslatifyDarkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            if (bottomNavUiData.canShow) {
                                BottomBar(
                                    navItems = bottomNavUiData.navItems,
                                    navController = navController
                                )
                            }
                        }
                    ) { paddingValues ->
                        Navigator.Navigate(
                            navController = navController,
                            scaffoldPadding = paddingValues
                        )
                    }
                }
            }
        }
    }
}