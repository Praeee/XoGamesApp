package com.example.xogamesapp.domain

import com.example.xogamesapp.data.model.GameHistoryRepository
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.game.model.toGameHistory
import com.example.xogamesapp.mapper.AppDataMappers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

fun interface GetGameHistoryByIdUseCase {
    fun getAllGameHistory(id : Int): Flow<GameHistory>
}

class GetGameHistoryByIdUseCaseImpl @Inject constructor(
    private val repository: GameHistoryRepository,
) : GetGameHistoryByIdUseCase {
    override fun getAllGameHistory(id : Int): Flow<GameHistory> {
        val result = repository.getGameById(id).map { it.toGameHistory() }
        return result
    }
}