package com.example.remicalculator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.remicalculator.RemiCalculatorScreen

@Composable
fun AddPlayersScreen(
    navController: NavController,
    viewModel: RemiCalculatorViewModel

) {
    // ime igre = game (od prej)
    val gameId = navController.previousBackStackEntry?.arguments?.getLong("gameId") ?: return

    val game by viewModel.getGameById(gameId).collectAsState(initial = null)

    // število igralcev od prej
    val numberOfPlayers = game?.numberOfPlayers ?: 0
    var playerNames = rememberSaveable { mutableStateListOf(*Array(numberOfPlayers) { "" }) }


    if (game == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Loading...")
        }
        return
    }

    if (numberOfPlayers == 0) {
        Text("No players available.")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Dodaj igralce")

        //Spacer(modifier = Modifier.height(32.dp))

        for (i in 0 until numberOfPlayers) {
            OutlinedTextField(
                value = playerNames.getOrElse(0) { "" },
                onValueChange = { newName ->
                    if (i < playerNames.size) {
                        playerNames[i] = newName
                    }
                },
                label = { Text("Vnesi ime igralca 1") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
            )
            /*OutlinedTextField(
                value = playerNames[i],
                onValueChange = { newName: String ->
                    playerNames[i] = newName
                },
                label = { Text("Vnesi ime igralca ${i + 1}") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
            )*/

            //Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(onClick = {
            game?.let {
                val updatedGame = it.copy(players = playerNames)
                viewModel.updateGame(updatedGame)
                navController.navigate("${RemiCalculatorScreen.PlayGame.name}/$gameId")
            }
        }) {
            Text(text = "Potrdi")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(onClick = {navController.navigateUp()}) {
            Text(
                text = "Nazaj"
            )
        }
    }
}