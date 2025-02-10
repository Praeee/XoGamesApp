package com.example.xogamesapp.domain

import com.example.xogamesapp.data.model.GameHistoryRepository
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.game.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

fun interface InsertGameHistoryUseCase {
    fun insertGameHistory(gameHistory: GameHistory): Flow<Unit>
}

class InsertGameHistoryUseCaseImpl @Inject constructor(
    private val repository: GameHistoryRepository
) : InsertGameHistoryUseCase {
    override fun insertGameHistory(gameHistory: GameHistory): Flow<Unit> {
        val gameHistory = gameHistory.toEntity()
        return flow {
            emit(repository.insertGameHistory(gameHistory))
        }
    }
}