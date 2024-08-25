package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togetherapp.R
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.ui.components.IconButtonBack

@Composable
fun ShowAllTopBar(title: String, onHideAllClick: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButtonBack(onHideAllClick)
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp
        )
    }
}