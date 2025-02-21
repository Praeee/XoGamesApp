package com.example.xogamesapp.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GameHistoryDao {
    @Insert
    fun insertGameHistory(gameHistoryEntity: GameHistoryEntity)

    @Update
    fun updateGameHistory(gameHistoryEntity: GameHistoryEntity)

    @Query("SELECT * FROM game_history ORDER BY createDate DESC")
    fun getAllGameHistory(): Flow<List<GameHistoryEntity>>

    @Query("SELECT * FROM game_history WHERE id = :id")
    fun getGameById(id: Int): Flow<GameHistoryEntity>

    @Delete
    fun deleteGameHistory(gameHistoryEntity: GameHistoryEntity)

}
