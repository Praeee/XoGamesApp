package com.example.xogamesapp.mapper

import com.example.data.database.GameHistoryEntity
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.utils.DataMapper

data class AppDataMappers (
    val gameHistoryToEntityMapper: DataMapper<GameHistory, GameHistoryEntity>,
    val gameHistoryEntityToModelMapper: DataMapper<GameHistoryEntity, GameHistory>
)