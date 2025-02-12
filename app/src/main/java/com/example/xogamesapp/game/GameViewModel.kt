package com.example.xogamesapp.game

import android.os.Build
import android.util.Log
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
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
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

    fun setHistory(history: List<List<MutableState<String>>>,winnerName: String) {
        val history = history.map { mutableStates -> mutableStates.map { it.value } }
        setState {
            copy(
                history = history,
                winnerName = winnerName
            )
        }
        saveGameHistory()
    }

    private fun saveGameHistory() {
        val state = uiState.value
            val request = GameHistory(
                history = state.history,
                winner = state.winnerName,
                createDate = Date.from(Instant.now())
            )
            insertGameHistoryUseCase.insertGameHistory(request)
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.d("GameViewModel", "Start game history")
                }
                .onEach {
                    Log.d("GameViewModel", "Game history inserted")
                }
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
