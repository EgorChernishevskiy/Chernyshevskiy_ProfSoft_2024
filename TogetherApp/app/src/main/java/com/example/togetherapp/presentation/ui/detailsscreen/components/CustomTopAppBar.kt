package com.example.togetherapp.presentation.ui.detailsscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import coil.compose.rememberImagePainter
import com.example.togetherapp.R
import com.example.togetherapp.presentation.state.DetailsScreenState
import com.example.togetherapp.presentation.ui.components.IconButtonBack

@Composable
fun CustomTopAppBar(
    state: DetailsScreenState,
    onBackClick: () -> Unit,
    courseIndex: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFFFFD80C))
    ) {
        Column(modifier = Modifier.padding(top = 8.dp)) {
            state.course?.let { course ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButtonBack(onBackClick)
                    Text(
                        text = "Лекция ${courseIndex + 1}",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Column(modifier = Modifier.padding( horizontal = 16.dp))
                {
                    course.title.split(" ").forEach { word ->
                        Text(
                            text = word,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = course.description,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color(0xFF806B00),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}