package com.example.remicalculator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.remicalculator.RemiCalculatorScreen
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SavedGamesScreen(
    navController: NavController,
    viewModel: RemiCalculatorViewModel = hiltViewModel()
) {
    val savedGames = viewModel.getAllGames().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Button(onClick = {viewModel.deleteAllGames()},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
            Text(
                text = "Izbriši vse igre"
            )
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

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
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Button(onClick = { navController.navigate(RemiCalculatorScreen.Home.name) },
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(0.dp),
            contentPadding = PaddingValues(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        )
        {
            Text(text = "<- Nazaj")
        }
        Spacer(
            modifier = Modifier.height(64.dp)
        )
    }

}


