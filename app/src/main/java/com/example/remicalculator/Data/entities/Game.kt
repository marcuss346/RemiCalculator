package com.example.remicalculator.Data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val numberOfPlayers: Int,
    val date: Date,
    val players: List<String>,
    val scores: List<List<Int>>
)