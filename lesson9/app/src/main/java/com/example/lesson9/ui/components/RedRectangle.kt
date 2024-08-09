package com.example.lesson9.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RedRectangle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(260.dp, 112.dp)
            .background(
                color = Color.Red,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    bottomEnd = 32.dp
                )
            )
    )
}
