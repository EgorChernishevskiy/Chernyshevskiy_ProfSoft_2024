package com.example.togetherapp.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.togetherapp.R

@Composable
fun IconButtonBack(onHideAllClick: () -> Unit) {
    IconButton(
        modifier = Modifier
            .padding(start = 6.dp),
        onClick = { onHideAllClick() }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back_arrow),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}