package com.example.medicinetracker

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.medicinetracker.screens.homeNavGraph
import com.example.medicinetracker.screens.loginGraph

@Composable
fun MedicineNavHost(
    navController: NavHostController,
    onNavigateToDestination: (String, String?) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "login",
        route = "auth"
    ) {

        loginGraph(
            navigateToHome = { email ->
                onNavigateToDestination("homeGraph", "home/$email")
            })

        homeNavGraph(
            navigateToAuth = {
                onNavigateToDestination("auth", "login")
            }, navigateToDetail = {
                onNavigateToDestination("homeGraph", "detail")
            })

    }
}
