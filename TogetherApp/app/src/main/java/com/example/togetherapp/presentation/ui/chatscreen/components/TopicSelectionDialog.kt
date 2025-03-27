package com.example.togetherapp.presentation.ui.chatscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.togetherapp.domain.utils.NoteTopic
import com.example.togetherapp.presentation.utils.getTopicName

@Composable
fun TopicSelectionDialog(
    onTopicSelected: (NoteTopic) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Выберите тему чата") },
        text = {
            Column {
                NoteTopic.values().forEach { topic ->
                    Button(
                        onClick = { onTopicSelected(topic) },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD80C))
                    ) {
                        Text(
                            getTopicName(topic),
                            color = Color.Black,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD80C))
            ) {
                Text(
                    "Закрыть",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleSmall
                )

            }
        }
    )
}