package com.example.xogamesapp.game

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import com.example.xogamesapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<GameUiState>(GameUiState()) {

    fun setSize(size: Int) {
        setState {
            copy(size = size)
        }
    }

    fun setHistory(history: List<List<MutableState<String>>>) {
        val history = history.map { mutableStates -> mutableStates.map { it.value } }
        setState {
            copy(history = history)
        }
    }

}

data class GameUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val size: Int = 0,
    val history: List<List<String>> = emptyList(),
)

data class GameNavEvent(
    val onNavigateBack: () -> Unit = {},
)
