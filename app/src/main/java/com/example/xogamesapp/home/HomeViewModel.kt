package com.example.xogamesapp.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import com.example.xogamesapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<HomeUiState>(HomeUiState()) {


}

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String = "",
)


data class HomeNavEvent(
    val onClickStartGame: () -> Unit = {},
    val onClickHistory: () -> Unit = {},
)
