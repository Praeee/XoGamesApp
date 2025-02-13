package com.example.xogamesapp.game.model

import com.example.xogamesapp.data.model.GameHistoryEntity
import java.util.Date

data class GameHistory(
    val id: Int = 0,
    val history: List<List<String>>,
    val winner: String,
    val createDate: Date
)

fun GameHistory.toEntity(): GameHistoryEntity {
    return GameHistoryEntity(
        id = id,
        history = history,
        winner = winner,
        createDate = createDate
    )
}

fun GameHistoryEntity.toGameHistory(): GameHistory {
    return GameHistory(
        id = id,
        history = history,
        winner = winner,
        createDate = createDate
    )
}