package com.example.togetherapp.presentation.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togetherapp.presentation.state.CNoteDetailsScreenState
import com.example.togetherapp.presentation.utils.formatNoteDetailsDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteTopAppBar(
    state: CNoteDetailsScreenState,
    onBackClick: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(166.dp)
            .background(Color(0xFFFFD80C))
    ) {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            state.note?.let { note ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButtonBack(onBackClick)
                    Text(
                        text = "Заметка",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(47.dp))
                Column(modifier = Modifier.padding(horizontal = 16.dp))
                {
                    Text(
                        text = formatNoteDetailsDate(note.date),
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color(0xFF806B00),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = note.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}