package com.example.medicinetracker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()): AppState {
    return remember(navController) {
        AppState(navController)
    }
}

@Stable
class AppState(val navController: NavHostController) {

    val isLogin: Boolean
        @Composable get() = false

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    fun navigate(
        destination: String,
        route: String? = null,
    ) {
        navController.navigate(route ?: destination)
    }
}