package com.example.xogamesapp.data.model

import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton


interface GameHistoryRepository {
    suspend fun insertGameHistory(gameHistoryEntity: GameHistoryEntity)
    suspend fun updateGameHistory(gameHistoryEntity: GameHistoryEntity)
    fun getAllGameHistory(): Flow<List<GameHistoryEntity>>
    fun getGameById(id: Int): Flow<GameHistoryEntity?>
}

@Singleton
class GameHistoryRepositoryImpl(private val gameHistoryDao: GameHistoryDao) : GameHistoryRepository {

    override suspend fun insertGameHistory(gameHistoryEntity: GameHistoryEntity) {
        gameHistoryDao.insertGameHistory(gameHistoryEntity)
    }

    override suspend fun updateGameHistory(gameHistoryEntity: GameHistoryEntity) {
        gameHistoryDao.updateGameHistory(gameHistoryEntity)
    }

    override fun getAllGameHistory(): Flow<List<GameHistoryEntity>> {
        return gameHistoryDao.getAllGameHistory()
    }

    override fun getGameById(id: Int): Flow<GameHistoryEntity?> {
        return gameHistoryDao.getGameById(id)
    }


}
