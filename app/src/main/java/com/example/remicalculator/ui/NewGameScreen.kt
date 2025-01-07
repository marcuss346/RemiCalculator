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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.remicalculator.RemiCalculatorScreen

@Composable
fun NewGameScreen(
    navController: NavController,
    viewModel: RemiCalculatorViewModel = hiltViewModel()

) {
    var game by remember { mutableStateOf("") }
    var players by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Ustvari novo igro"
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        OutlinedTextField(
            value = game,
            onValueChange = { game = it },
            label = { Text(text = "Vnesi ime igre") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = players,
            onValueChange = {
                players = it
                isValid = isValidText(players)
            },
            label = { Text("Število igralcev") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
        )

        if(!isValid){
            Text("Polje lahko vsebuje samo številke", color = Color.Red)
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(
            onClick = {
                if (isValid) {
                    Log.d("NewGameScreen", "Button clicked") // izpis
                    viewModel.addGame(game, players.toInt()) { gameId ->
                        Log.d("NewGameScreen", "gameId returned: $gameId")
                        navController.navigate("${RemiCalculatorScreen.AddPlayers.name}/$gameId")
                    }
                }
            }
        ) {
            Text(text = "Potrdi")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Button(onClick = {navController.navigateUp()},
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

fun isValidText(text : String): Boolean {
    return text.matches(Regex("[0-9]+"));
}

