package com.example.togetherapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.togetherapp.domain.model.note.NoteContent

@Composable
fun NoteContentItem(noteContent: NoteContent) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 20.dp,
                bottom = 16.dp
            )
    ) {
        if (noteContent.text.isNotBlank()) {
            Text(
                text = noteContent.text,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Justify,
                color = Color.Gray
            )
        }
        noteContent.image?.let { imageUrl ->
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(193.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
        }
    }
}
