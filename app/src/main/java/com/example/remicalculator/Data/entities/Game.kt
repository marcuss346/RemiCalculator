package com.example.remicalculator.Data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.remicalculator.Data.dao.GameDao
import java.util.Date

@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Date,
    val players: List<String>,
    val scores: List<Int>
)