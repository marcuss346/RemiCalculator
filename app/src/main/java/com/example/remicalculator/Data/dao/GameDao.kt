package com.example.remicalculator.Data.dao

import androidx.room.*
import com.example.remicalculator.Data.entities.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    // Insert a new game
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Game)

    // Retrieve all games, sorted by date
    @Query("SELECT * FROM games ORDER BY date DESC")
    fun getAllGames(): Flow<List<Game>>

    // Delete a game by ID
    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun deleteGameById(gameId: Int)

    // Clear all games
    @Query("DELETE FROM games")
    suspend fun clearAllGames()
}