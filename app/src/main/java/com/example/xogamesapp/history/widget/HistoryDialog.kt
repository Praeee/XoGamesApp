package com.example.xogamesapp.history.widget

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.xogamesapp.game.model.GameHistory
import java.util.Date

@Composable
fun HistoryItemDialog(
    state: GameHistory,
    onClearHistory: () -> Unit
) {

    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, "shared result xo game")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current


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
                .background(
                    if (state.winner == "X") Color(0xFFBBDFC8)
                    else Color(0xFFF0E5D8)
                )
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Winner is : ${state.winner}",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            HistoryGrid(
                history = state.history.map { row -> row.map { mutableStateOf(it) } },
                size = state.history.size
            )

            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Date : ${state.createDate}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }



            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        context.startActivity(shareIntent)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                    )
                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = "Share",
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "save",
                    )
                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = "Save",
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }
    }
}

@Preview
@Composable
fun HistoryItemDialogPreview() {
    val history = GameHistory(
        id = 1,
        history = listOf(
            listOf("X", "O", "X"),
            listOf("O", "X", "O"),
            listOf("X", "O", "X")
        ),
        winner = "X",
        createDate = Date()
    )
    HistoryItemDialog(state = history, onClearHistory = {})
}