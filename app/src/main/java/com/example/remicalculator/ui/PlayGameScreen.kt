package com.example.remicalculator.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.remicalculator.RemiCalculatorScreen
import androidx.compose.material3.Scaffold
import androidx.compose.ui.text.style.TextAlign


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayGameScreen(
    navController: NavController,
    gameId: Long,
    viewModel: RemiCalculatorViewModel

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

        Spacer(modifier = Modifier.height(64.dp))

        game.value?.let { gameData ->

            Text(
                text = "Ime igre: ${gameData.name}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            gameData.players.forEachIndexed { playerIndex, playerName ->

                Text(text = playerName, style = MaterialTheme.typography.bodyLarge)
                Text(text = "-------------", style = MaterialTheme.typography.bodyMedium)

                gameData.scores.getOrNull(playerIndex)?.forEachIndexed { gameIndex, score ->
                    Text(
                        text = "partija ${gameIndex + 1}: $score",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                val totalPoints = gameData.scores.getOrNull(playerIndex)?.sum() ?: 0
                Text(text = "-------------", style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = "Skupne točke: $totalPoints",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                var pointsToAdd by remember { mutableStateOf("") }
                val keyboardController = LocalSoftwareKeyboardController.current

                TextField(
                    value = pointsToAdd,
                    onValueChange = { newValue ->
                        pointsToAdd = newValue.filter { it.isDigit() || it == '-' }
                    },
                    label = { Text("Dodaj točke") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal,
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
                    Text(text = "Dodaj točke")
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            Button(onClick = {
                Log.d("PlayGameScreen", "Deleting game with ID: $gameId")
                viewModel.deleteGame(gameId)
                navController.navigate(RemiCalculatorScreen.SavedGames.name)
            }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                Text(text = "Izbriši igro")
            }
        } ?: run {
            // loading message
            Text("Loading game data or game not found.")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Button(onClick = { navController.navigate(RemiCalculatorScreen.SavedGames.name) },
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(0.dp),
            contentPadding = PaddingValues(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        )
        {
            Text(text = "<- Nazaj", textAlign = TextAlign.Left)
        }
    }
}
/*
@Preview
@Composable
fun AlertDialogSample() {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false // lahko zbriševa če ne želiva da se zapre ko klikneš mimo
            },
            title = { Text(text = "Title") },
            text = { Text(text = "Turned on by default") },
            confirmButton = {
                TextButton(onClick = { openDialog.value = false }) { Text("Potrdi") }
            },
            dismissButton = {
                TextButton(onClick = { openDialog.value = false }) { Text("Zavrni") }
            }
        )
    }
}*/