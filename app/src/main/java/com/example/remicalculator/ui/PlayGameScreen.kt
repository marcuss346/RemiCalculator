package com.example.remicalculator.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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

            Text(
                text = "Game Name: ${gameData.name}",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            gameData.players.forEachIndexed { playerIndex, playerName ->

                Text(text = "-------------", style = MaterialTheme.typography.bodyMedium)
                Text(text = playerName, style = MaterialTheme.typography.bodyLarge)
                Text(text = "-------------", style = MaterialTheme.typography.bodyMedium)

                gameData.scores.getOrNull(playerIndex)?.forEachIndexed { gameIndex, score ->
                    Text(
                        text = "Game ${gameIndex + 1}: $score",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                val totalPoints = gameData.scores.getOrNull(playerIndex)?.sum() ?: 0
                Text(text = "-------------", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "Total Points: $totalPoints",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = "-------------", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))

                var pointsToAdd by remember { mutableStateOf("") }
                val keyboardController = LocalSoftwareKeyboardController.current

                TextField(
                    value = pointsToAdd,
                    onValueChange = { newValue ->
                        pointsToAdd = newValue.filter { it.isDigit() || it == '-' }
                    },
                    label = { Text("Enter Points") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(onClick = {
                    val points = pointsToAdd.toIntOrNull() ?: 0
                    val updatedScores = gameData.scores.toMutableList()
                    if (updatedScores.size > playerIndex) {
                        updatedScores[playerIndex] = updatedScores[playerIndex] + points
                    } else {
                        updatedScores.add(listOf(points))
                    }

                    viewModel.updateGame(gameData.copy(scores = updatedScores))
                }) {
                    Text(text = "Add Points")
                }
            }

            Button(onClick = { navController.navigate(RemiCalculatorScreen.SavedGames.name) }) {
                Text(text = "Nazaj")
            }

            Spacer(modifier = Modifier.height(64.dp))

            Button(onClick = {
                Log.d("PlayGameScreen", "Deleting game with ID: $gameId")
                viewModel.deleteGame(gameId)
                navController.navigate(RemiCalculatorScreen.SavedGames.name)
            }) {
                Text(text = "Izbriši igro")
            }
        } ?: run {
            // loading message
            Text("Loading game data or game not found.")
        }
    }
}