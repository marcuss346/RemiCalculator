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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.remicalculator.RemiCalculatorScreen

@Composable
fun NewGameScreen(
    navController: NavController
) {
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
            onValueChange = { players = it
                            isValid = isValidText(players)},
            label = { Text("Number of players") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
        )

        if(!isValid){
            Text("Feild can only contain numbers", color = Color.Red)
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(onClick = {navController.navigate(RemiCalculatorScreen.AddPlayers.name)}) { // gre na screen ustvarjene igre
            Text(
                text = "Potrdi"
            )
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

fun isValidText(text : String): Boolean {
    return text.matches(Regex("[0-9]+"));
}

