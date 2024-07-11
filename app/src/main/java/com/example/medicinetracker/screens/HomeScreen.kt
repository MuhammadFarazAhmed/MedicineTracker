package com.example.medicinetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medicinetracker.common.ApiStatus.ERROR
import com.example.medicinetracker.common.ApiStatus.IDLE
import com.example.medicinetracker.common.ApiStatus.LOADING
import com.example.medicinetracker.common.ApiStatus.SUCCESS
import com.example.medicinetracker.components.NormalTextComponent
import com.example.medicinetracker.components.collectAsStateLifecycleAware
import com.example.medicinetracker.model.MedicationEntity
import com.example.medicinetracker.model.ProblemEntity
import com.example.medicinetracker.ui.theme.AccentColor
import com.example.medicinetracker.ui.theme.WhiteColor
import com.example.medicinetracker.vm.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    email: String?,
    vm: HomeViewModel = koinViewModel(),
) {

    Surface(
        color = WhiteColor,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(28.dp)
    ) {

        val pullRefreshState = rememberPullRefreshState(vm.isRefreshing.value, { })
        val problems by vm.problems.collectAsStateLifecycleAware()


        Column {

            HeaderComposable(email)

            when (vm.status.value) {
                IDLE -> {}
                SUCCESS -> {
                    vm.isRefreshing.value = false
                    SuccessComposable(problems, pullRefreshState, navController, vm)
                }

                ERROR -> {
                    vm.isRefreshing.value = false
                    ErrorComposable(errorMessage = vm.errorMessage.value)
                }

                LOADING -> {
                    vm.isRefreshing.value = true
                    LoadingComposable()
                }
            }

        }

    }
}


@Composable
fun LoadingComposable() {
    Box(
        contentAlignment = Center, modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(modifier = Modifier.size(52.dp), color = AccentColor, 5.dp)
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SuccessComposable(
    problems: List<ProblemEntity>,
    pullRefreshState: PullRefreshState,
    navController: NavHostController,
    vm: HomeViewModel
) {
    Box(
        contentAlignment = TopStart,
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(problems) { problem ->
                ProblemListItem(problem) {
                    vm.currentItem = problem
                    navController.navigate("detail")
                }
            }
        }
    }
}

@Composable
fun ErrorComposable(errorMessage: String) {
    Box(
        contentAlignment = Center, modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = errorMessage, color = AccentColor, textAlign = TextAlign.Center)
    }
}

@Composable
fun HeaderComposable(email: String?) {
    val currentTime = remember { mutableStateOf(Calendar.getInstance().time) }

    val greeting = remember(currentTime.value) {
        val calendar = Calendar.getInstance()
        calendar.time = currentTime.value
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        when (hour) {
            in 0..11 -> "Good Morning ☀\uFE0F"
            in 12..17 -> "Good Afternoon ☀\uFE0F"
            else -> "Good Evening \uD83C\uDF19"
        }
    }

    email?.let {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            NormalTextComponent(value = "$greeting \n $email")
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {

    Surface(
        color = WhiteColor,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(28.dp)
    ) {

        Column {

            HeaderComposable("faraz@gmail.com")

            Box(
                contentAlignment = TopStart,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn {
                    items(10) { _ ->
                        ProblemListItem(
                            ProblemEntity(
                                1,
                                "Diabetes",
                                listOf(
                                    MedicationEntity(
                                        1,
                                        name = "Aspirin",
                                        dose = "twice",
                                        strength = "500mg"
                                    )
                                )
                            )
                        ) {

                        }
                    }
                }
            }

        }
    }
}