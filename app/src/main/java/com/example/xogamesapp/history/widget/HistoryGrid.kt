package com.example.xogamesapp.history.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xogamesapp.game.widget.TicTacToeCell

@Composable
fun HistoryGrid(
    history: List<List<MutableState<String>>>,
    size: Int
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cellSize = screenWidth / size / 2

    Column(
        modifier = Modifier.wrapContentSize().padding(vertical = 16.dp).padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        for (i in 0 until size) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (j in 0 until size) {
                    TicTacToeCell(
                        symbol = history[i][j].value,
                        cellSize = cellSize,
                        onClick = {} // No action needed for history display
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun HistoryGridPreview() {
    val history =  List(3) { List(3) { mutableStateOf("") } }
    HistoryGrid(history = history, size = 3)
}