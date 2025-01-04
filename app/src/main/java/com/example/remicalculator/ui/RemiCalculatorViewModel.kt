package com.example.remicalculator.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remicalculator.Data.GameRepository
import com.example.remicalculator.Data.Translation
import com.example.remicalculator.Data.dao.GameDao
import com.example.remicalculator.Data.entities.Game
import com.example.remicalculator.network.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RemiCalculatorViewModel @Inject constructor (private val gameRepository : GameRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(RemiCalculatorUIState())
    val uiState: StateFlow<RemiCalculatorUIState> = _uiState.asStateFlow()

    init {
        resetRemiCalc()
    }

    private fun resetRemiCalc() {
        _uiState.value = RemiCalculatorUIState()
    }

    fun updateTextLanguage(){
        viewModelScope.launch {
            try {
                val translation = Translation("auto", "en", _uiState.value.rulesSlovenian)
                val newQuote = Api.retrofitService.getTranslation(translation)
                _uiState.update {
                    currentState ->
                    newQuote.body()?.let { currentState.copy(rulesSlovenian = it.trans) }!!
                }
                Log.i("API", "Quote retrieved successfully + $newQuote")
            } catch (e: IOException) {
                Log.e("API", "Error retrieving the quote - IO Exception: " + e.message)
            } catch (e: HttpException) {
                Log.e("API", "Error retrieving the quote - HTTP Exception: " + e.message)
            }

        }
    }

    /*fun addGame(name: String, numberOfPlayers: Int, onGameAdded: (Long) -> Unit) {
        viewModelScope.launch {
            val newGame = Game(
                id = 0L, // ID will be autogenerated
                name = name,
                numberOfPlayers = numberOfPlayers,
                date = Date(), // Current date and time
                players = List(numberOfPlayers) { "" },
                scores = List(numberOfPlayers) { 0 }
            )
            val gameId = gameDao.insertGame(newGame)
            onGameAdded(gameId)
        }
    }*/
    /*fun addGame(name: String, numberOfPlayers: Int) {
        viewModelScope.launch {
            val newGame = Game(
                name = name,
                numberOfPlayers = numberOfPlayers,
                date = Date(),
                scores = emptyList(),
                players = emptyList(),
            )
            gameDao.insertGame(newGame)
        }
    }*/
    fun addGame(name: String, numberOfPlayers: Int, onGameAdded: (Long) -> Unit) {
        viewModelScope.launch{
            val newGame = Game(
                name = name,
                numberOfPlayers = numberOfPlayers,
                date = Date(),
                players = List(numberOfPlayers) { "" },
                scores = List(numberOfPlayers) { 0 }
            )
            Log.d("ViewModel", "Attempting to add game: $name")
            val gameId = gameRepository.insertGame(newGame)
            Log.d("ViewModel", "Game added with ID: $gameId")
            onGameAdded(gameId)
        }
    }

    /*suspend fun addGame(name: String, numberOfPlayers: Int, onGameAdded: (Long) -> Unit) {
        // Add the game using the repository
        val gameId = gameRepository.insertGame(Game(
            name = name,
            numberOfPlayers = numberOfPlayers,
            date = Date(),
            players = List(numberOfPlayers) { "" },
            scores = List(numberOfPlayers) { 0 }))

        // Invoke the callback with the gameId
        onGameAdded(gameId)
    }*/

    /*fun getGameById(gameId: Long) = gameRepository.getGameById(gameId)*/
    /* fun getGameById(gameId: Long): Flow<Game?> {
         return gameDao.getGameById(gameId)
     }*/
    /*fun getGameById(id: Long): Flow<Game?> = gameRepository.getGameById(id)*/
    fun getGameById(gameId: Long): Flow<Game?> {
        return gameRepository.getGameById(gameId)
    }

    // Update game with player names
    fun updateGame(game: Game) {
        viewModelScope.launch {
            gameRepository.updateGame(game)
        }
    }
    /*suspend fun updateGame(game: Game) {
        gameRepository.updateGame(game)
    }*/

    fun getAllGames(): Flow<List<Game>> {
        return gameRepository.getAllGames()
    }

    fun deleteAllGames() {
        viewModelScope.launch {
            gameRepository.deleteAllGames()
        }
    }

}