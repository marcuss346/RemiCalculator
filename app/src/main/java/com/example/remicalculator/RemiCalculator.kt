package com.example.remicalculator

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.remicalculator.ui.RemiCalculatorViewModel

enum class RemiCalculatorScreen {
    Home,
    SavedGames,
    PlayGame,
    NewGame,
    AddPlayers,
    GameRules
}

@Composable
fun RemiCalcApp(
    navController: NavHostController = rememberNavController(),
    gameId: Long,
    viewModel: RemiCalculatorViewModel = hiltViewModel()
) {

    NavHost(
        navController = navController,
        startDestination = RemiCalculatorScreen.Home.name
    ) {
        composable(route = RemiCalculatorScreen.Home.name) {
            //val viewModel: RemiCalculatorViewModel = hiltViewModel()
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = RemiCalculatorScreen.SavedGames.name) {
            SavedGamesScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = "${RemiCalculatorScreen.PlayGame.name}/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.LongType })
        ) { backStackEntry ->
            PlayGameScreen(
                navController = navController,
                gameId = backStackEntry.arguments?.getLong("gameId") ?: 0L,
                viewModel = viewModel
            )
        }
        composable(route = RemiCalculatorScreen.NewGame.name) {
            NewGameScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = "${RemiCalculatorScreen.AddPlayers.name}/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.LongType })
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getLong("gameId") ?: 0L
            AddPlayersScreen(navController, gameId, viewModel)
        }
        composable(route = RemiCalculatorScreen.GameRules.name) {
            GameRulesScreen(viewModel = viewModel, navController = navController)
        }
    }
}