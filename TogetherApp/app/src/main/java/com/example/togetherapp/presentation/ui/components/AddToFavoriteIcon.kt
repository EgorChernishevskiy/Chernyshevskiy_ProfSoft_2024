package com.example.togetherapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.togetherapp.R

@Composable
fun AddToFavoriteIcon(isLiked: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },

    ) {
        if (isLiked) {
            Icon(
                painter = painterResource(R.drawable.favorite_liked),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .width(36.dp)
                    .height(36.dp)
            )
        }
        else{
            Icon(
                painter = painterResource(R.drawable.favorite_unliked),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .width(36.dp)
                    .height(36.dp)
            )
        }
    }
}