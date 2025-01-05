package com.example.remicalculator

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
    //viewModel: RemiCalculatorViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    gameId: Long
) {

    NavHost(
        navController = navController,
        startDestination = RemiCalculatorScreen.Home.name
    ) {
        composable(route = RemiCalculatorScreen.Home.name) {
            HomeScreen(navController = navController)
        }
        composable(route = RemiCalculatorScreen.SavedGames.name) {
            val viewModel: RemiCalculatorViewModel = hiltViewModel()
            SavedGamesScreen(navController = navController, viewModel = viewModel)
        }
        /*composable(route = "${RemiCalculatorScreen.PlayGame.name}/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getLong("gameId") ?: return@composable
            val viewModel: RemiCalculatorViewModel = hiltViewModel()
            PlayGameScreen(navController = navController, gameId = gameId, viewModel = viewModel)
        }*/
        composable(
            route = "${RemiCalculatorScreen.PlayGame.name}/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.LongType })
        ) { backStackEntry ->
            PlayGameScreen(
                navController = navController,
                gameId = backStackEntry.arguments?.getLong("gameId") ?: 0L
            )
        }
        composable(route = RemiCalculatorScreen.NewGame.name) {
            val viewModel: RemiCalculatorViewModel = hiltViewModel()
            NewGameScreen(navController = navController, viewModel = viewModel)
        }
        /*composable(route = RemiCalculatorScreen.AddPlayers.name) {
            Log.d("NewGameScreen", "Navigating to AddPlayers with gameId: $gameId") // izpis
            val viewModel: RemiCalculatorViewModel = hiltViewModel()
            AddPlayersScreen(navController = navController, viewModel = viewModel)
        }*/
        /*composable("${RemiCalculatorScreen.AddPlayers.name}/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")?.toLong() ?: 0L
            val viewModel: RemiCalculatorViewModel = hiltViewModel()
            AddPlayersScreen(navController, gameId, viewModel)
        }*/
        composable(
            route = "${RemiCalculatorScreen.AddPlayers.name}/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.LongType })
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getLong("gameId") ?: 0L
            AddPlayersScreen(navController, gameId)
        }
        composable(route = RemiCalculatorScreen.GameRules.name) {
            val viewModel: RemiCalculatorViewModel = hiltViewModel()
            GameRulesScreen(viewModel = viewModel, navController = navController)
        }
    }
}