package com.example.medicinetracker

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.medicinetracker.screens.homeNavGraph
import com.example.medicinetracker.screens.loginGraph


@Composable
fun MedicineNavHost(
    navController: NavHostController,
    onNavigateToDestination: (String, String?) -> Unit,
    isLogin: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if(isLogin) "homeGraph" else "authGraph",
    ) {

        loginGraph(
            navigateToHome = { email ->
                navController.navigate("home/$email"){
                    popUpTo("authGraph")
                }
            })

        homeNavGraph(
            navigateToAuth = {
                navController.navigate("authGraph"){
                    popUpTo("homeGraph")
                }
            }, navigateToDetail = {
                onNavigateToDestination("homeGraph", "detail")
            })

    }
}
