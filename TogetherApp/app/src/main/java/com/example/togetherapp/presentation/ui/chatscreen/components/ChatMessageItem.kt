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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatMessageItem(message: ChatMessage) {
//    val backgroundColor = if (isCurrentUser) Color.Yellow else Color.LightGray
//    val horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
//    val paddingStart = if (isCurrentUser) 0.dp else 8.dp
//    val paddingEnd = if (isCurrentUser) 8.dp else 0.dp
    val backgroundColor = if (message.author.role == 2) Color(0xFFFFD80C) else Color(0x66D7D7D7)
    val horizontalArrangement = Arrangement.Start
    val paddingStart = 8.dp
    val paddingEnd = 0.dp

    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM HH:mm", Locale.getDefault())
    val date = LocalDateTime.parse(message.date, inputFormatter)
    val formattedDate = date.format(outputFormatter)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(backgroundColor)
        ) {

            Image(
                painter = rememberImagePainter(message.author.avatar),
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
                    .widthIn(max = 240.dp)
            ) {
                Text(
                    text = "${message.author.name} ${message.author.surname}",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = message.message,
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
    }
}

//{
//    Text(
//        text = "${message.author.name} ${message.author.surname}",
//        fontWeight = FontWeight.Bold,
//        textAlign = if (isCurrentUser) TextAlign.End else TextAlign.Start
//    )
//    Text(
//        text = message.message,
//        color = Color.Black,
//        textAlign = if (isCurrentUser) TextAlign.End else TextAlign.Start
//    )
//    Text(
//        text = message.date.format(dateFormat),
//        style = MaterialTheme.typography.bodySmall,
//        textAlign = if (isCurrentUser) TextAlign.End else TextAlign.Start
//    )
//}

//        if (isCurrentUser) {
//            Image(
//                painter = rememberImagePainter(message.author.avatar),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(40.dp)
//                    .padding(paddingEnd),
//                contentScale = ContentScale.Crop
//            )
//        }