package com.example.remicalculator.Data;

import android.util.Log
import com.example.remicalculator.Data.dao.GameDao
import com.example.remicalculator.Data.entities.Game
import kotlinx.coroutines.flow.Flow

class GameRepository(private val gameDao: GameDao) {

    // Insert a new game and return its ID
    suspend fun insertGame(game: Game): Long {
        Log.d("GameRepository", "Inserting game: $game")
        return gameDao.insertGame(game)
    }

    // Update an existing game
    suspend fun updateGame(game: Game) {
        gameDao.updateGame(game)
    }

    // Get a game by its ID
    fun getGameById(gameId: Long): Flow<Game?> {
        return gameDao.getGameById(gameId)
    }

    fun getAllGames(): Flow<List<Game>> {
        return gameDao.getAllGames()
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

    suspend fun deleteGame(gameId: Long) {
        return gameDao.deleteGameById(gameId)
    }
}