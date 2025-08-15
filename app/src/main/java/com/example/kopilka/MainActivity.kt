package com.example.kopilka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kopilka.ui.screens.HomeScreen
import com.example.kopilka.ui.screens.SettingsScreen
import com.example.kopilka.ui.theme.KopilkaTheme
import com.example.kopilka.viewmodel.MainViewModel
import com.example.kopilka.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KopilkaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val nav = rememberNavController()
                    val mainVm: MainViewModel = viewModel()
                    val settingsVm: SettingsViewModel = viewModel()
                    NavHost(navController = nav, startDestination = "home") {
                        composable("home") { HomeScreen(nav, mainVm, settingsVm) }
                        composable("settings") { SettingsScreen(nav, settingsVm) }
                    }
                }
            }
        }
    }
}
