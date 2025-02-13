package com.example.xogamesapp.history.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HistoryItem(
    winnerName : String,
    onClickItem : () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClickItem()
            }
    ) {
        Text(
            modifier = Modifier
                .align(alignment = androidx.compose.ui.Alignment.CenterHorizontally)
                .padding(16.dp)
            ,
            text = "Winner is -> $winnerName"
        )
    }
}

@Preview
@Composable
fun PreviewHistoryItem() {
    HistoryItem(
        winnerName = "X"
    )
}