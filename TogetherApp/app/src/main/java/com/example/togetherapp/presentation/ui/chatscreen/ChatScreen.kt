package com.example.togetherapp.presentation.ui.chatscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.togetherapp.presentation.ui.chatscreen.components.ChatScreenContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen() {
    ChatScreenContent()
}