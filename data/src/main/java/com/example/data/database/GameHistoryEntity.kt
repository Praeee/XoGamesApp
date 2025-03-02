package com.example.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.GameHistoryConverters
import java.util.Date

@Entity(tableName = "game_history")
@TypeConverters(GameHistoryConverters::class)
data class GameHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val history: List<List<String>>,  // 2D list to store game board states
    val winner: String,                // Store the winner ("X", "O", or "Draw")
    val createDate: Date
)
