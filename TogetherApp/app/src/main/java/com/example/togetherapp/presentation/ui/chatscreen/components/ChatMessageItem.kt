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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatMessageItem(message: ChatMessage) {
//    val backgroundColor = if (isCurrentUser) Color.Yellow else Color.LightGray
//    val horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
//    val paddingStart = if (isCurrentUser) 0.dp else 8.dp
//    val paddingEnd = if (isCurrentUser) 8.dp else 0.dp
    val backgroundColor = if (message.author.role == 2) Color.Yellow else Color(0x66D7D7D7)
    val horizontalArrangement = Arrangement.Start
    val paddingStart = 8.dp
    val paddingEnd = 0.dp
    val dateFormat = DateTimeFormatter.ofPattern("dd MMM HH:mm", Locale.getDefault())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = horizontalArrangement
    ) {

        Image(
            painter = rememberImagePainter(message.author.avatar),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(paddingStart),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .padding(start = paddingStart, end = paddingEnd)
                .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
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
        Text(
            text = message.date.format(dateFormat),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Start
        )

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