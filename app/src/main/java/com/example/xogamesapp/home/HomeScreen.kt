package com.example.xogamesapp.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xogamesapp.ui.theme.XoGamesAppTheme
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    navEvent: HomeNavEvent,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = homeViewModel.uiState.value
    HomeScreenContent(
        state = uiState,
        onClickStartGame = {
            navEvent.onClickStartGame()
        },
        onClickHistory = {
            navEvent.onClickHistory()
        }
    )
}

@Composable
fun HomeScreenContent(
    state: HomeUiState,
    onClickStartGame: () -> Unit = {},
    onClickHistory: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
        ) {
            Button(
                onClick = { onClickStartGame() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Start Game!",
                    modifier = Modifier.padding(8.dp)
                )
            }
            Button(
                onClick = { onClickHistory() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(
                    text = "History",
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    XoGamesAppTheme {
        HomeScreenContent(
            state = HomeUiState()
        )
    }
}
