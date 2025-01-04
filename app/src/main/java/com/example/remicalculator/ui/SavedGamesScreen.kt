package com.example.remicalculator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.remicalculator.RemiCalculatorScreen
import androidx.compose.runtime.collectAsState

@Composable
fun SavedGamesScreen(
    navController: NavController,
    viewModel: RemiCalculatorViewModel
) {
    val savedGames = viewModel.getAllGames().collectAsState(initial = emptyList())

    //val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            //.verticalScroll(scrollState),
        //verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Seznam iger"
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(savedGames.value) { game ->
                Button(
                    onClick = {
                        navController.navigate("${RemiCalculatorScreen.PlayGame.name}/${game.id}")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = game.name
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }


        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(onClick = {navController.navigate(RemiCalculatorScreen.Home.name)}) {
            Text(
                text = "Nazaj"
            )
        }
    }
}