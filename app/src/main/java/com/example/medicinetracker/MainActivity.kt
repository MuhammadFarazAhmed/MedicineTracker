package com.example.medicinetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.medicinetracker.ui.theme.MedicineTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MedicineTrackerTheme {
                App()
            }
        }
    }
}

@Composable
fun App(appState: AppState = rememberAppState()) {

    Scaffold(modifier = Modifier,
        topBar = { },
        content = { padding ->

            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxHeight()
            ) {

                MedicineNavHost(
                    navController = appState.navController,
                    onNavigateToDestination = appState::navigate
                )
            }

        },
        bottomBar = { })
}