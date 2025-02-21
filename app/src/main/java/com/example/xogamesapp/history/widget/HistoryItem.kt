package com.example.xogamesapp.history.widget

import androidx.compose.foundation.clickable
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

@Composable
fun HistoryItem(
    id : Int,
    winnerName : String,
    onClickItem : () -> Unit = {},
    onDeleteItem : (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClickItem()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
                text = "Winner is -> $winnerName"
            )

            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onDeleteItem(id)
                    },
                tint = androidx.compose.ui.graphics.Color.Red,
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
            )
        }

    }
}

@Preview
@Composable
fun PreviewHistoryItem() {
    HistoryItem(
        winnerName = "X",
        id = 1
    )
}