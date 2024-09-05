package com.example.togetherapp.presentation.ui.profilescreen.components

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
fun ButtonAllUsers(onShowUsersClick: () -> Unit) {
    IconButton(
        modifier = Modifier.padding(end = 16.dp),
        onClick = { onShowUsersClick() }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_all_users),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}