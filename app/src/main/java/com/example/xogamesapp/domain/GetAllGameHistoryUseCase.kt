package com.example.xogamesapp.domain

import com.example.xogamesapp.data.model.GameHistoryRepository
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.game.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

fun interface GetAllGameHistoryUseCase {
    fun getAllGameHistory(): Flow<List<GameHistory>>
}

class GetAllGameHistoryUseCaseImpl @Inject constructor(
    private val repository: GameHistoryRepository
) : GetAllGameHistoryUseCase {
    override fun getAllGameHistory(): Flow<List<GameHistory>> {
        val result = repository.getAllGameHistory().map { list ->
            list.map { it.toModel() }
        }
        return result
    }
}