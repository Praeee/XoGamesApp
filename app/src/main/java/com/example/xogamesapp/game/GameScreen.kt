package com.example.xogamesapp.game

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.xogamesapp.home.HomeNavEvent
import com.example.xogamesapp.home.HomeUiState
import com.example.xogamesapp.home.HomeViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GameScreen(
    navEvent: GameNavEvent,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    val uiState = gameViewModel.uiState.value
    GameScreenContent(
        state = uiState,
    )
}

@Composable
fun GameScreenContent(
    state: GameUiState,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Game!",
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    }
}