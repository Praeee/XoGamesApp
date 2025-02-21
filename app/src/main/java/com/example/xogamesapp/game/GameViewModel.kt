package com.example.xogamesapp.game

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.xogamesapp.domain.InsertGameHistoryUseCase
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import java.time.Instant
import java.util.Date
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val insertGameHistoryUseCase: InsertGameHistoryUseCase
) : BaseViewModel<GameUiState>(GameUiState()) {

    fun setSize(size: Int) {
        setState {
            copy(size = size)
        }
    }

    fun setHistory(history: List<List<MutableState<String>>>, winnerName: String) {
        val historyMap = history.map { mutableStates -> mutableStates.map { it.value } }
        val request = GameHistory(
            history = historyMap,
            winner = winnerName,
            createDate = Date.from(Instant.now()),
        )
        insertGameHistoryUseCase.insertGameHistory(request)
            .flowOn(Dispatchers.IO)
            .onCompletion {
                delay(5000L)
                setSize(0)
            }
            .launchIn(viewModelScope)

    }

}

data class GameUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val size: Int = 0,
    val history: List<List<String>> = emptyList(),
    val winnerName: String = "",
)

data class GameNavEvent(
    val onNavigateBack: () -> Unit = {},
)
