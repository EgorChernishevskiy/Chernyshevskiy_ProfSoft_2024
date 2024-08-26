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
fun CustomSearchButton(){
    IconButton(
        onClick = {  },
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFD6B714))
            .width(36.dp)
            .height(36.dp),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_searchnormal),
            contentDescription = null,
            modifier = Modifier
                .width(16.dp)
                .height(16.dp)
        )
    }
}
