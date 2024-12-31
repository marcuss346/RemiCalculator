package com.example.remicalculator


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.remicalculator.ui.HomeScreen
import com.example.remicalculator.ui.SavedGamesScreen
import com.example.remicalculator.ui.PlayGameScreen
import com.example.remicalculator.ui.NewGameScreen
import com.example.remicalculator.ui.AddPlayersScreen
import com.example.remicalculator.ui.GameRulesScreen
import androidx.navigation.NavHostController

enum class RemiCalculatorScreen {
    Home,
    SavedGames,
    PlayGame,
    NewGame,
    AddPlayers,
    GameRules
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
        composable(route = RemiCalculatorScreen.SavedGames.name) {
            SavedGamesScreen(navController = navController)
        }
        composable(route = RemiCalculatorScreen.PlayGame.name) {
            PlayGameScreen(navController = navController)
        }
        composable(route = RemiCalculatorScreen.NewGame.name) {
            NewGameScreen(navController = navController)
        }
        composable(route = RemiCalculatorScreen.AddPlayers.name) {
            AddPlayersScreen(navController = navController)
        }
        composable(route = RemiCalculatorScreen.GameRules.name) {
            GameRulesScreen(navController = navController)
        }
    }
}