package com.example.remicalculator


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.remicalculator.ui.HomeScreen
import com.example.remicalculator.ui.NewGameScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

enum class RemiCalculatorScreen() {
    Home,
    NewGame
}

@Composable
fun RemiCalcApp(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = RemiCalculatorScreen.Home.name
    ) {
        composable(route = RemiCalculatorScreen.Home.name) {
            HomeScreen(navController = navController)
        }
        composable(route = RemiCalculatorScreen.NewGame.name) {
            NewGameScreen(navController = navController)
        }
    }
}