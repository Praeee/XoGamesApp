package com.example.xogamesapp.mapper

import com.example.xogamesapp.data.model.GameHistoryEntity
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.utils.DataMapper

data class AppDataMappers (
    val gameHistoryToEntityMapper: DataMapper<GameHistory, GameHistoryEntity>,
    val gameHistoryEntityToModelMapper: DataMapper<GameHistoryEntity, GameHistory>
)