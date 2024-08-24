package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomTitleText(title: String) {
    val words = title.split(" ")
    val firstLine = StringBuilder()
    val secondLine = StringBuilder()
    val thirdLine = StringBuilder()

    for (word in words) {
        when {
            firstLine.isEmpty() || firstLine.length + word.length <= 20 -> firstLine.append("$word ")
            secondLine.isEmpty() || secondLine.length + word.length <= 20 -> secondLine.append("$word ")
            else -> thirdLine.append("$word ")
        }
    }

    Column {
        Text(
            text = firstLine.toString().trim(),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = secondLine.toString().trim(),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = thirdLine.toString().trim(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF806B00)
        )
    }
}