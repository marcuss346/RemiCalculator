package com.example.remicalculator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen (navController: NavController) {
    Column(){
        Text(
            text = "Hello world!"
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(onClick = {}) {
            Text(text = "Dont click!")
        }

    }
}