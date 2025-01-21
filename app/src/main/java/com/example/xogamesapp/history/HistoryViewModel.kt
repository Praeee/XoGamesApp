package com.example.xogamesapp.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import com.example.xogamesapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<HistoryUiState>(HistoryUiState()) {


}

data class HistoryUiState(
    val isLoading: Boolean = false,
    val error: String = "",
)


data class HistoryNavEvent(
    val onNavigateBack: () -> Unit = {},
)