package com.example.togetherapp.presentation.ui.chatscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.togetherapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInput(
    onSendClicked: (String) -> Unit,
    onRefreshClicked: () -> Unit,
) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.White)
            .border(BorderStroke(1.dp, Color.LightGray)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Сообщение", color = Color.Gray) },
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.Black,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(end = 8.dp)
        ) {
            IconButton(onClick = { onRefreshClicked() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_refresh),
                    contentDescription = "Refresh",
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(onClick = { onSendClicked(text); text = "" }) {
                Icon(
                    painter = painterResource(R.drawable.ic_send),
                    contentDescription = "Send",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}