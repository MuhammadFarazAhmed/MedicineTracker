package com.example.medicinetracker

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.medicinetracker.screens.DetailScreen
import com.example.medicinetracker.screens.HomeScreen
import com.example.medicinetracker.screens.LoginScreen
import com.example.medicinetracker.vm.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val vm: HomeViewModel = getViewModel()

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable(route = "login") {
            LoginScreen(navController)
        }
        composable(route = "welcome/{email}") { backStackEntry ->
            HomeScreen(navController, email = backStackEntry.arguments?.getString("email"), vm)
        }

        composable(route = "detail") {
            DetailScreen(vm)
        }
    }
}