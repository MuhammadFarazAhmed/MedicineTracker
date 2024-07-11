package com.example.medicinetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicinetracker.model.MedicationEntity
import com.example.medicinetracker.model.ProblemEntity
import com.example.medicinetracker.ui.theme.WhiteColor
import com.example.medicinetracker.vm.HomeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun DetailScreen(vm: HomeViewModel = koinViewModel()) {

    Surface(
        color = WhiteColor,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(28.dp)
    ) {
        vm.currentItem?.let { ProblemListItem(it) { } }
    }
}

@Composable
@Preview
fun DetailScreen() {
    val currentItem = ProblemEntity(
        1,
        "Diabete",
        listOf(
            MedicationEntity(
                1,
                name = "Aspirin",
                dose = "twice",
                strength = "500mg"
            )
        )
    )
    Surface(
        color = WhiteColor,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(28.dp)
    ) {
        currentItem.let { ProblemListItem(it) { } }
    }
}