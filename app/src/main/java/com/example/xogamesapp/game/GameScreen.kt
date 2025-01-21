package com.example.xogamesapp.game

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GameScreen(
    navEvent: GameNavEvent,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    val uiState = gameViewModel.uiState.collectAsState().value
    GameScreenContent(
        state = uiState,
        onSizeChange = { size ->
            gameViewModel.setSize(size)
        },
        onClickRestartGame = {
            gameViewModel.setSize(0)
        }
    )
}

@Composable
fun GameScreenContent(
    state: GameUiState,
    onSizeChange: (Int) -> Unit = {},
    onClickRestartGame: () -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
        ) {
            if (state.size == 0) {
                GameSizeInput(
                    onSizeChange = { size ->
                        onSizeChange.invoke(size.toIntOrNull() ?: 0)
                    },
                )
            } else {

                Text(
                    text = "Game size: ${state.size}",
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 16.dp)
                )
                Button(
                    onClick = { onClickRestartGame() },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Restart Game!",
                    )
                }
                TicTacToeBoard(size = state.size)



            }

        }
    }
}

@Composable
fun GameSizeInput(
    onSizeChange: (String) -> Unit = {},
) {
    var text by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text("Enter number") },
        keyboardActions = KeyboardActions(
            onDone = {
                onSizeChange.invoke(text)
                keyboardController?.hide()
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        maxLines = 1
    )
}

@Composable
fun TicTacToeBoard(size: Int) {
    // Get the screen width
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Calculate the cell size based on the screen width and board size
    val cellSize = screenWidth / size

    Column(
        modifier = Modifier.fillMaxSize()  // Make the board fill the screen
    ) {
        for (i in 0 until size) {
            Row(
                modifier = Modifier.fillMaxWidth(),  // Ensure each row fills the width
                horizontalArrangement = Arrangement.Center  // Center the cells
            ) {
                for (j in 0 until size) {
                    TicTacToeCell(cellSize)
                }
            }
        }
    }
}

@Composable
fun TicTacToeCell(cellSize: Dp) {
    Box(
        modifier = Modifier
            .size(cellSize)  // Dynamic cell size based on the screen width and board size
            .border(1.dp, Color.Black)
            .clickable { /* Handle click */ },
        contentAlignment = Alignment.Center
    ) {
        // Add XO mark here if needed
    }
}


@Preview
@Composable
fun GameScreenPreview() {
    GameScreenContent(
        state = GameUiState(size = 3)
    )
}