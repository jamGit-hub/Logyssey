package com.example.logyssey.navigation

import android.util.Log

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import androidx.navigation.navArgument

import com.example.logyssey.data.GameViewModel
import com.example.logyssey.screens.DashboardScreen
import com.example.logyssey.screens.GameDetailScreen
import com.example.logyssey.ui.screens.SplashScreen
import com.example.logyssey.screens.HomeScreen
import com.example.logyssey.screens.SettingsScreen
import com.example.logyssey.screens.components.AppBottomBar
import com.example.logyssey.screens.exploreScreen


@Composable
fun MainApp(darkTheme : Boolean
            ,onToggleTheme: () -> Unit,
            currentLanguage: String,
            onLanguageChange: (String) -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = when (currentRoute) {
        "home", "explore", "Dashboard", "settings" -> true
        else -> false
    }
    val viewModel: GameViewModel = viewModel()


// Debug the boolean itself
    Log.d("NavDebug", "Current Route: $currentRoute | Show Bottom Bar: $showBottomBar")


    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                AppBottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash", // my app starts here
            modifier = Modifier.padding(innerPadding)
        ) {


            //  route
            composable("splash") {
                SplashScreen(onTimeout = {
                    navController.navigate("home") {

                        popUpTo("splash") {
                            inclusive = true
                        }// so user can't go "back" to the splash screen


                    }
                })
            }
            composable("home") {
                HomeScreen(navController = navController, onThemeToggle = onToggleTheme, viewModel)
            }

            composable("explore") { exploreScreen(viewModel, navController) }

            composable("Dashboard") {
                DashboardScreen(navController, viewModel)
            }

            composable("settings") {
                SettingsScreen(
                    isDarkTheme = darkTheme,
                    onToggleTheme = onToggleTheme,
                    currentLanguage = currentLanguage,
                    onLanguageChange = onLanguageChange, //localization logic
                    navController = navController
                )
            }

            composable(
                route = "game_detail/{gameId}?openSheet={openSheet}",
                arguments = listOf(
                    navArgument("gameId") {
                        type = NavType.StringType
                    },
                    navArgument("openSheet") {
                        type = NavType.BoolType
                        defaultValue = false
                    }
                )
            ) { backStackEntry ->
                val gameId = backStackEntry.arguments?.getString("gameId")
                val openSheet = backStackEntry.arguments?.getBoolean("openSheet") ?: false

                GameDetailScreen(
                    gameId = gameId,
                    navController = navController,
                    viewModel = viewModel,
                    currentLanguage = currentLanguage,
                    openSheet = openSheet
                )
            }
        }
    }
}