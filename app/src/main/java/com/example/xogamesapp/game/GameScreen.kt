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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.xogamesapp.game.widget.TicTacToeCell

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
        },
        onSaveHistoryGame = { history , winner ->
            gameViewModel.setHistory(history,winner)
        }
    )
}

@Composable
fun GameScreenContent(
    state: GameUiState,
    onSizeChange: (Int) -> Unit = {},
    onClickRestartGame: () -> Unit = {},
    onSaveHistoryGame: (List<List<MutableState<String>>>, String) -> Unit = { _, _ -> }
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
                TicTacToeBoard(
                    size = state.size,
                    onSaveHistoryGame = { history , winner ->
                        onSaveHistoryGame.invoke(history , winner)
                    }
                )

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
fun TicTacToeBoard(
    size: Int,
    onSaveHistoryGame: (List<List<MutableState<String>>>, String) -> Unit = { _, _ -> }
) {
    val board by remember {
        mutableStateOf(List(size) { MutableList(size) { mutableStateOf("") } })
    }
    var isXTurn by remember { mutableStateOf(true) }
    var winner by remember { mutableStateOf<String?>(null) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cellSize = screenWidth / size

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        winner?.let {
            Text(
                text = if ((it.contains("X")) or (it.contains("O"))) "$it Wins!" else it,
                fontSize = 30.sp,
                color = Color.Green,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            onSaveHistoryGame.invoke(board,it)
        }

        for (i in 0 until size) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (j in 0 until size) {
                    TicTacToeCell(
                        symbol = board[i][j].value,
                        cellSize = cellSize,
                        onClick = {
                            if (board[i][j].value == "" && winner == null) {
                                board[i][j].value = if (isXTurn) "X" else "O"
                                isXTurn = !isXTurn
                                winner = checkWinner(board, size)
                            }
                        }
                    )
                }
            }
        }

    }
}

// Function to check if there is a winner
fun checkWinner(board: List<List<MutableState<String>>>, size: Int): String? {
    // Check rows
    for (i in 0 until size) {
        if (board[i].all { it.value == "X" }) return "X"
        if (board[i].all { it.value == "O" }) return "O"
    }

    // Check columns
    for (j in 0 until size) {
        if ((0 until size).all { board[it][j].value == "X" }) return "X"
        if ((0 until size).all { board[it][j].value == "O" }) return "O"
    }

    // Check diagonals
    if ((0 until size).all { board[it][it].value == "X" }) return "X"
    if ((0 until size).all { board[it][it].value == "O" }) return "O"

    if ((0 until size).all { board[it][size - it - 1].value == "X" }) return "X"
    if ((0 until size).all { board[it][size - it - 1].value == "O" }) return "O"

    if (board.flatten().all { it.value != "" }) return "Draw"

    // No winner
    return  null
}



@Preview
@Composable
fun GameScreenPreview() {
    GameScreenContent(
        state = GameUiState(size = 3)
    )
}