package com.example.xogamesapp.history

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.history.widget.HistoryGrid
import com.example.xogamesapp.history.widget.HistoryItem
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HistoryScreen(
    navEvent: HistoryNavEvent,
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState = historyViewModel.uiState.collectAsState().value
    HistoryScreenContent(
        state = uiState,
        onClickItem = {
            historyViewModel.getHistoryItem(it)
        },
        onDeleteHistoryItem = {
            historyViewModel.deleteHistoryItem(it)
        },
        onClearHistory = {
            historyViewModel.clearHistory()
        }
    )

}

@Composable
fun HistoryScreenContent(
    state: HistoryUiState,
    onClickItem: (id: Int) -> Unit = {},
    onDeleteHistoryItem : (id : Int) -> Unit = {},
    onClearHistory : () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()

    ) {
        if (state.history != null) {
            Dialog(
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                ),
                onDismissRequest = {
                    onClearHistory.invoke()
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize()
                        .background(Color.White)
                        .clip(MaterialTheme.shapes.medium)
                        .border(2.dp, Color.LightGray, MaterialTheme.shapes.medium)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {

                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear History",
                            modifier = Modifier
                                .clickable { onClearHistory.invoke() }
                                .padding(32.dp)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Winner is : ${state.history.winner}",
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }

                    HistoryGrid(
                        history = state.history.history.map { row -> row.map { mutableStateOf(it) } },
                        size = state.history.history.size
                    )
                }
            }

        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {

                Text(
                    text = "History",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(32.dp),
                    fontSize = 24.sp,
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    items(state.historyList, key = { it.id }) { history ->
                        HistoryItem(
                            winnerName = history.winner,
                            onClickItem = { onClickItem.invoke(history.id) },
                            onDeleteItem = { onDeleteHistoryItem.invoke(history.id) },
                            id = history.id
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<Float>,
    color: Color,
) {
    Canvas(
        modifier = modifier,
        onDraw = {
            val step = size.width / (data.size - 1)
            val max = data.maxOrNull() ?: 1f
            val min = data.minOrNull() ?: 0f
            val yStep = size.height / (max - min)
            val path = android.graphics.Path()
            path.moveTo(0f, size.height - (data.first() - min) * yStep)
            data.forEachIndexed { index, value ->
                path.lineTo(index * step, size.height - (value - min) * yStep)
            }
            drawLine(
                color = color,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = 2.dp.toPx(),
            )
        }
    )
}

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    data: List<Pair<String, Float>>,
    colors: List<Color>,
) {
    Canvas(
        modifier = modifier,
        onDraw = {
            var startAngle = 0f
            data.forEachIndexed { index, (_, percentage) ->
                drawArc(
                    color = colors[index],
                    startAngle = startAngle,
                    sweepAngle = 360 * percentage,
                    useCenter = true,
                    topLeft = Offset(0f, 0f),
                    size = Size(size.width, size.height)
                )
                startAngle += 360 * percentage
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    HistoryScreenContent(
        state = HistoryUiState(
            historyList = listOf(
                GameHistory(1, history = listOf(listOf("X", "O", "X"), listOf("O", "X", "O"), listOf("X", "O", "X")), winner = "X", createDate = Date()),
            )
        )
    )
}