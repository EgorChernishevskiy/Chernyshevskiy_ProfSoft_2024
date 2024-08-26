package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togetherapp.R
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.ui.components.CustomSearchButton
import com.example.togetherapp.presentation.ui.components.IconButtonBack

@Composable
fun ShowAllTopBar(title: String, onHideAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(Color(0xFFFFD80C)),
    ) {
        Row(
            modifier = Modifier.padding(top = 8.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButtonBack(onHideAllClick)
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            CustomSearchButton()
        }
    }
}