package com.example.remicalculator.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.remicalculator.RemiCalculatorScreen

@Composable
fun PlayGameScreen(
    navController: NavController,
    gameId: Long,
    viewModel: RemiCalculatorViewModel = hiltViewModel()

) {
    val game = viewModel.getGameById(gameId).collectAsState(initial = null)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        game.value?.let { gameData ->
            // Display Game Name
            Text(text = "Game Name: ${gameData.name}", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Number of Players: ${gameData.numberOfPlayers}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))

            if (gameData.scores.isNotEmpty()) {
                gameData.scores.forEachIndexed { index, score ->
                    Text(text = "Player ${index + 1}: Score = $score")
                }
            } else {
                Text(text = "No scores available yet.")
            }
        }

        Button(onClick = {}) {
            Text(text = "Dodaj točke")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Finish Game and Delete Game buttons as before
        Button(onClick = { navController.navigate(RemiCalculatorScreen.SavedGames.name) }) {
            Text(text = "Nazaj")
        }

        Button(onClick = {
            Log.d("PlayGameScreen", "Finish game clicked for $gameId")
        }) {
            Text(text = "Končaj igro")
        }

        Button(onClick = {
            Log.d("PlayGameScreen", "Game deleted")
        }) {
            Text(text = "Izbriši igro")
        }
    }

    ?: run { // loading message
            Text("Loading game data or game not found.")
    }
}