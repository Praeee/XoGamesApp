package com.example.xogamesapp.game

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
        }
    )
}

@Composable
fun GameScreenContent(
    state: GameUiState,
    onSizeChange: (Int) -> Unit = {}
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
                        onSizeChange.invoke(size.toIntOrNull()?:0)
                    },
                )
            } else {
                Text(
                    text = "Game size: ${state.size}"
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
            onSizeChange.invoke(it)
            keyboardController?.hide()
        },
        label = { Text("Enter number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreenContent(
        state = GameUiState()
    )
}