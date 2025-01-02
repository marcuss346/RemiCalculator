package com.example.remicalculator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GameRulesScreen(
    viewModel: RemiCalculatorViewModel = viewModel(),
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val uiState = viewModel.uiState.collectAsState().value

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Pravila igre"
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(
            text = uiState.rulesSlovenian
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        /*Text(
            text = "Torej k pravilom igre remi: vsak igralec ob štartu igre dobi določeno število " +
                    "igralnih kart. Končni namen igre je čim prej odvreči vse svoje karte začenši z odprtjem " +
                    "in/ali z dodajanjem kart po igralni mizi ali drugim igralcem ali na skupen kupček za odlaganje " +
                    "igralnih kart. Odprtje pravzaprav pomeni: igralec se lahko odpre na dva načina - 1) sestaviti mora " +
                    "barvno lestvico (to so minimalno tri zaporedne karte iste barve ali t. i. tris (na primer " +
                    "tris kraljev KKK v križu, srcu in kari). Skupen znesek teh kart mora običajno znašati vsaj 51 " +
                    "točk, če se igralec želi odpreti, to pravilo je še posebej v veljavi v Sloveniji. Točkovanje kart " +
                    "gre po znanem vrstnem redu, pravila remija pravijo: številke na kartah so vredne toliko točk, kot " +
                    "je vredna številka na karti, karte s podobami (Janez, Dama in Kralj) pa so vredne po 10 točk. As je " +
                    "bodisi vreden eno točko, bodisi 10 ali 11 točk, odvisno od posamezne igralne igre. Kjer se iga z " +
                    "jokerji, so le-ti vredni po 25 točk. Sleherni igralec lahko igralne karte bodisi dodaja v že " +
                    "odprte karte drugih igralcev remija šele, ko je tudi sam odprt, prej ne. "
        )*/

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(onClick = {navController.navigateUp()}) {
            Text(
                text = "Nazaj"
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Button(onClick = {
            viewModel.updateTextLanguage()
        }) {
            Text(
                text = "Prevedi"
            )
        }
    }
}
