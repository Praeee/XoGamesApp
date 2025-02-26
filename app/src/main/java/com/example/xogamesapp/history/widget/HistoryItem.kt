package com.example.xogamesapp.history.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xogamesapp.game.model.GameHistory

@Composable
fun HistoryItem(
    history: GameHistory,
    onClickItem : () -> Unit = {},
    onDeleteItem : (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onClickItem()
            }
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = if (history.winner == "X") androidx.compose.ui.graphics.Color(0xFFBBDFC8)
                    else androidx.compose.ui.graphics.Color(0xFFF0E5D8)
                )
                .padding(8.dp)
                .padding(vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
                    text = "Winner is -> ${history.winner}"
                )

                Icon(
                    modifier = Modifier
                        .clickable {
                            onDeleteItem(history.id)
                        },
                    tint = androidx.compose.ui.graphics.Color.Red,
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                )
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                fontSize = 12.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
                text = "${history.createDate}"
            )

        }


    }
}

@Preview
@Composable
fun PreviewHistoryItem() {
    HistoryItem(
        history = GameHistory(
            id = 1,
            history = listOf(
                listOf("X", "O", "X"),
                listOf("O", "X", "O"),
                listOf("X", "O", "X")
            ),
            winner = "X",
            createDate = java.util.Date()
        )
    )
}