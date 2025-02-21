package com.example.xogamesapp.domain

import com.example.xogamesapp.data.model.GameHistoryRepository
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.game.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

fun interface DeleteGameHistoryByIdUseCase {
    fun deleteGameHistory(gameHistoryItem : GameHistory): Flow<Unit>
}

class DeleteGameHistoryByIdUseCaseImpl @Inject constructor(
    private val repository: GameHistoryRepository,
) : DeleteGameHistoryByIdUseCase {
    override fun deleteGameHistory(gameHistoryItem : GameHistory): Flow<Unit> {
        return flow {
            emit(repository.deleteGameHistory(gameHistoryItem.toEntity()))
        }
    }
}