package com.example.xogamesapp.game

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import com.example.xogamesapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<GameUiState>(GameUiState()) {


}

data class GameUiState(
    val isLoading: Boolean = false,
    val error: String = "",
)

data class GameNavEvent(
    val onNavigateBack: () -> Unit = {},
)
