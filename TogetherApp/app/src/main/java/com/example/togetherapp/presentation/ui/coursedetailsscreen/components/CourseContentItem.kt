package com.example.togetherapp.presentation.ui.coursedetailsscreen.components

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
import com.example.togetherapp.domain.model.course.CourseText

@Composable
fun CourseContentItem(courseText: CourseText) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        if (courseText.text.isNotBlank()) {
            Text(
                text = courseText.text,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Justify,
                color = Color.Gray
            )
        }
        courseText.image?.let { imageUrl ->
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