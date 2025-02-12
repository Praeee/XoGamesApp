package com.example.xogamesapp.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.xogamesapp.domain.GetAllGameHistoryUseCase
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllGameHistoryUseCase: GetAllGameHistoryUseCase
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


}

data class HistoryUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val historyList : List<GameHistory> = emptyList()
)


data class HistoryNavEvent(
    val onNavigateBack: () -> Unit = {},
)