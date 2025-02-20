package com.example.xogamesapp.game.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicTacToeCell(symbol: String, cellSize: Dp, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(cellSize)
            .border(1.dp, Color.Black)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = if (symbol == "X") Color.Red else Color.Blue
        )
    }
}
