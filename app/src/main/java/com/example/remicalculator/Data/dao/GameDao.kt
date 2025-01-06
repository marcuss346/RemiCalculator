package com.example.remicalculator.Data.dao

import androidx.room.*
import com.example.remicalculator.Data.entities.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    // get all games
    @Query("SELECT * FROM games ORDER BY date DESC")
    fun getAllGames(): Flow<List<Game>>

    // delete by id
    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun deleteGameById(gameId: Long)

    // delete all
    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    // get game by id
    @Query("SELECT * FROM games WHERE id = :gameId LIMIT 1")
    fun getGameById(gameId: Long): Flow<Game?>

    // new game
    @Insert
    suspend fun insertGame(game: Game): Long

    @Update
    suspend fun updateGame(game: Game)
}