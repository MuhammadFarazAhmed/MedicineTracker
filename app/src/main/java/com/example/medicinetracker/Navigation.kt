package com.example.medicinetracker

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicinetracker.screens.DetailScreen
import com.example.medicinetracker.screens.HomeScreen
import com.example.medicinetracker.screens.LoginScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable(route = "login") {
            LoginScreen(navController)
        }
        composable(route = "welcome/{email}") { backStackEntry ->
            HomeScreen(navController, email = backStackEntry.arguments?.getString("email"))
        }

        composable(route = "detail") {
            DetailScreen()
        }
    }
}