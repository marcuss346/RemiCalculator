package com.example.remicalculator


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.remicalculator.ui.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

enum class StepCounterScreen() {
    Home
}

@Composable
fun RemiCalcApp(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = StepCounterScreen.Home.name
    ) {
        composable(route = StepCounterScreen.Home.name) {
            HomeScreen(navController = navController)
        }
    }
}