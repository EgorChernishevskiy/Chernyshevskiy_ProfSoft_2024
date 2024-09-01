package com.example.togetherapp.presentation.ui.details.components

import android.os.Build
import android.util.Log
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
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.presentation.state.note.LNoteDetailsScreenState
import com.example.togetherapp.presentation.state.note.NoteDetailsScreenState
import com.example.togetherapp.presentation.ui.components.AddToFavoriteIcon
import com.example.togetherapp.presentation.ui.components.IconButtonBack
import com.example.togetherapp.presentation.utils.formatNoteDetailsDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun <T> NoteTopAppBar(
    state: NoteDetailsScreenState<T>,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
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
                    Spacer(modifier = Modifier.weight(1f))

                    AddToFavoriteIcon(state.isFavorite, onFavoriteClick)
                }
                Spacer(modifier = Modifier.height(47.dp))

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    when (note) {
                        is Note -> {
                            NoteDateAndTitle(title = note.title, date = note.date)
                        }

                        is LocNote -> {
                            NoteDateAndTitle(title = note.title, date = note.date)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
