package com.example.xogamesapp.game

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.xogamesapp.data.model.GameHistoryEntity
import com.example.xogamesapp.data.model.GameHistoryRepository
import com.example.xogamesapp.data.model.OfflineGamesRepository
import com.example.xogamesapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
//    private val gamesRepository: GameHistoryRepository
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
        val gameHistoryEntity = GameHistoryEntity(
            history = state.history,
            winner = state.winnerName
        )
//        viewModelScope.launch {
//            gamesRepository.insertGameHistory(gameHistoryEntity)
//        }
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
