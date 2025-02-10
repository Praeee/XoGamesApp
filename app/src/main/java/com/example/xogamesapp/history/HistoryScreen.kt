package com.example.xogamesapp.history

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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

            PieChart(
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp),
                data = listOf(
                    "Player 1" to 0.3f,
                    "Player 2" to 0.2f,
                    "Player 3" to 0.5f,
                ),
                colors = listOf(
                    Color.Red,
                    Color.Green,
                    Color.Blue,
                )
            )

            LineChart(
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp),
                data = listOf(0.1f, 0.2f, 0.3f, 0.4f, 0.5f),
                color = Color.Red
            )

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
        state = HistoryUiState()
    )
}