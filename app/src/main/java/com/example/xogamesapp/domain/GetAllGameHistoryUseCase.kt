package com.example.xogamesapp.domain

import com.example.data.database.GameHistoryRepository
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.mapper.AppDataMappers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

fun interface GetAllGameHistoryUseCase {
    fun getAllGameHistory(): Flow<List<GameHistory>>
}

class GetAllGameHistoryUseCaseImpl @Inject constructor(
    private val repository: GameHistoryRepository,
    private val mappers: AppDataMappers,
) : GetAllGameHistoryUseCase {
    override fun getAllGameHistory(): Flow<List<GameHistory>> {
        val result = repository.getAllGameHistory().map { list ->
            list.map { mappers.gameHistoryEntityToModelMapper.map(it) }
        }
        return result
    }
}