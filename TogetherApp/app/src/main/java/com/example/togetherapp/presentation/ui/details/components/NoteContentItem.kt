package com.example.togetherapp.presentation.ui.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.togetherapp.R
import com.example.togetherapp.domain.model.comnote.NoteContent

@Composable
fun NoteContentItem(noteContent: NoteContent) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 20.dp,
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
        if (noteContent.image.isNotBlank()) {
            noteContent.image?.let { imageUrl ->
                Spacer(modifier = Modifier.height(8.dp))

                val imageRequest = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build()

                val painter = rememberImagePainter(imageRequest)

                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(193.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
            }
        }
    }
}
