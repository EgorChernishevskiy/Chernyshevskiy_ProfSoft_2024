package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.togetherapp.R

@Composable
fun SectionTitle(title: String, showAll: Boolean, onShowAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x7D7D7777))
            .fillMaxWidth()
            .height(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 11.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Spacer(
            modifier = Modifier
                .background(Color.White)
                .width(2.dp)
                .height(32.dp)
        )

        if (showAll) {
            TextButton(onClick = { onShowAllClick() }) {
                Text(
                    text = stringResource(R.string.button_all_text_label),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF646464)
                )
            }
        }
    }
}