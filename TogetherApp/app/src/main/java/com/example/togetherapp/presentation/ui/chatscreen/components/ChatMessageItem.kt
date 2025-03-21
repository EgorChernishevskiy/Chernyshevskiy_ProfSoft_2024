package com.example.togetherapp.presentation.ui.chatscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.togetherapp.domain.model.chat.ChatMessage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatMessageItem(message: ChatMessage, isCurrentUser: Boolean) {
    val horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
    val paddingStart = 12.dp
    val paddingEnd = 12.dp
    val backgroundColor = if (isCurrentUser) Color(0xFFFFD80C) else Color(0x66D7D7D7)

    // Используем правильный формат для парсинга временной метки
    val inputFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM HH:mm", Locale.getDefault())

    // Парсим временную метку
    val date = try {
        LocalDateTime.parse(message.timestamp, inputFormatter)
    } catch (e: DateTimeParseException) {
        // Если парсинг не удался, используем текущее время
        LocalDateTime.now()
    }

    val formattedDate = date.format(outputFormatter)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!isCurrentUser) {
            Row(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(backgroundColor)
            ) {
                // Аватар больше не используется, так как его нет в новом формате
                // Если нужно, можно добавить заглушку
                Image(
                    painter = rememberImagePainter(data = "https://example.com/avatar.png"), // Заглушка
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 20.dp))
                        .size(40.dp)
                        .padding(paddingStart),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier
                        .padding(end = paddingEnd)
                        .padding(8.dp)
                        .widthIn(max = 200.dp)
                ) {
                    Text(
                        text = message.sender, // Используем sender вместо author
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = message.text,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                }
            }
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 8.dp),
            )
        } else {
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 8.dp),
            )
            Row(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(backgroundColor)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = paddingStart, end = paddingEnd)
                        .padding(8.dp)
                        .widthIn(max = 200.dp)
                ) {
                    Text(
                        text = message.sender, // Используем sender вместо author
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = message.text,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                }
                // Аватар больше не используется, так как его нет в новом формате
                // Если нужно, можно добавить заглушку
                Image(
                    painter = rememberImagePainter(data = "https://example.com/avatar.png"), // Заглушка
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 20.dp))
                        .size(40.dp)
                        .padding(paddingStart),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

