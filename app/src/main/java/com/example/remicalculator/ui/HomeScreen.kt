package com.example.remicalculator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun HomeScreen (navController: NavController,
                viewModel: RemiCalculatorViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState().value

    if(uiState.report){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Napake javi na mi88203@student.uni-lj.si")
        }
    }else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Dobrodošli!",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(onClick = { navController.navigate(RemiCalculatorScreen.SavedGames.name) }) {
                Text(text = "Shranjene igre")
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(onClick = { navController.navigate(RemiCalculatorScreen.NewGame.name) }) {
                Text(
                    text = "Nova igra"
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(onClick = { navController.navigate(RemiCalculatorScreen.GameRules.name) }) {
                Text(
                    text = "Pravila igre"
                )
            }

        }
    }
}