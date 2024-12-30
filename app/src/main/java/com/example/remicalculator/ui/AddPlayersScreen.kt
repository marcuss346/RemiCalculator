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
    navController: NavController
) {
    // ime igre = game (od prej)
    // število igralcev od prej

    var player1 by remember { mutableStateOf("") }
    var player2 by remember { mutableStateOf("") }
    var player3 by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Dodaj igralce"
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        OutlinedTextField(
            value = player1,
            onValueChange = { player1 = it },
            label = { Text(text = "Vnesi ime igralca") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = player2,
            onValueChange = { player2 = it },
            label = { Text(text = "Vnesi ime igralca") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = player3,
            onValueChange = { player3 = it },
            label = { Text(text = "Vnesi ime igralca") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(onClick = {navController.navigate(RemiCalculatorScreen.PlayGame.name)}) { // gre na screen ustvarjene igre
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