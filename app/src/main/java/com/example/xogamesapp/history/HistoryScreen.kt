package com.example.xogamesapp.history

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HistoryScreen(
    navEvent: HistoryNavEvent,
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState = historyViewModel.uiState.collectAsState().value
    HistoryScreenContent(
        state = uiState,
    )
}

@Composable
fun HistoryScreenContent(
    state: HistoryUiState,
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
                    text = "History!",
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    }
}