package com.example.togetherapp.presentation.ui.details.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.togetherapp.presentation.utils.formatNoteDetailsDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteDateAndTitle(title: String, date: String) {
    Text(
        text = formatNoteDetailsDate(date),
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = Color(0xFF806B00),
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        maxLines = 1
    )
}