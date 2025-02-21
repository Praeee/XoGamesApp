package com.example.xogamesapp.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.xogamesapp.domain.DeleteGameHistoryByIdUseCase
import com.example.xogamesapp.domain.GetAllGameHistoryUseCase
import com.example.xogamesapp.domain.GetGameHistoryByIdUseCase
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllGameHistoryUseCase: GetAllGameHistoryUseCase,
    private val getGameHistoryByIdUseCase: GetGameHistoryByIdUseCase,
    private val deleteGameHistoryByIdUseCase: DeleteGameHistoryByIdUseCase
) : BaseViewModel<HistoryUiState>(HistoryUiState()) {

    init {
        getAllGameHistory()
    }

    private fun getAllGameHistory() {
        setState {
            copy(isLoading = true)
        }
        getAllGameHistoryUseCase.getAllGameHistory()
            .onEach { history ->
                setState {
                    copy(
                        isLoading = false,
                        historyList = history
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun getHistoryItem(id: Int) {
        getGameHistoryByIdUseCase.getAllGameHistory(id)
            .onEach { history ->
                setState {
                    copy(
                        isLoading = false,
                        history = history
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun clearHistory() {
        setState {
            copy(
                history = null
            )
        }
    }

    fun deleteHistoryItem(id: Int) {
        val getGameHistoryItem = uiState.value.historyList.find { it.id == id } ?: return
        deleteGameHistoryByIdUseCase.deleteGameHistory(getGameHistoryItem)
            .flowOn(Dispatchers.IO)
            .onEach {
                getAllGameHistory()
            }
            .launchIn(viewModelScope)
    }


}

data class HistoryUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val historyList: List<GameHistory> = emptyList(),
    val history: GameHistory? = null
)


data class HistoryNavEvent(
    val onNavigateBack: () -> Unit = {},
)