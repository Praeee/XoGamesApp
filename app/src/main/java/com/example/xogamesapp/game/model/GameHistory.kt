package com.example.xogamesapp.game.model

import com.example.xogamesapp.data.model.GameHistoryEntity
import java.util.Date

data class GameHistory(
    val history: List<List<String>>,
    val winner: String,
    val createDate: Date
)

fun GameHistory.toEntity(): GameHistoryEntity {
    return GameHistoryEntity(
        history = history,
        winner = winner,
        createDate = createDate
    )
}

fun GameHistoryEntity.toGameHistory(): GameHistory {
    return GameHistory(
        history = history,
        winner = winner,
        createDate = createDate
    )
}