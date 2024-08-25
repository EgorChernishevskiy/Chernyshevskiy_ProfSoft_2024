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
    val lines = arrayOf(StringBuilder(), StringBuilder(), StringBuilder())
    var currentLine = 0
    val maxLineLength = 20

    for (word in words) {
        if (lines[currentLine].length + word.length <= maxLineLength) {
            lines[currentLine].append("$word ")
        } else {
            currentLine++
            if (currentLine > 2) break
            lines[currentLine].append("$word ")
        }
    }

    Column {
        Text(
            text = lines[0].toString().trim(),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = lines[1].toString().trim(),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = lines[2].toString().trim(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF806B00)
        )
    }
}
