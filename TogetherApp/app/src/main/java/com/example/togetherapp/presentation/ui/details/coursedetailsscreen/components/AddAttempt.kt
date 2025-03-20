package com.example.togetherapp.presentation.ui.details.coursedetailsscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.togetherapp.R
import com.example.togetherapp.presentation.state.CourseDetailsScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAttempt(
    state: CourseDetailsScreenState,
    onTextChanged: (String) -> Unit,
    onImageUrlChanged: (String) -> Unit,
    onAttemptAdded: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            //.padding(8.dp)
    ) {
        // Поле для ввода текста
        TextField(
            value = state.attemptText,
            onValueChange = onTextChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            placeholder = {
                Text(
                    text = "Введите описание попытки",
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Row(
            modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Поле для ввода ссылки на изображение
            TextField(
                value = state.attemptImageUrl,
                onValueChange = onImageUrlChanged,
                modifier = Modifier
                    .weight(1f) // Занимает всё доступное пространство, кроме места для кнопки
                    .padding(end = 8.dp), // Добавляем отступ справа для кнопки
                placeholder = {
                    Text(
                        text = "Введите ссылку на изображение",
                        color = Color.Gray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.DarkGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            // Кнопка отправки
            IconButton(
                onClick = onAttemptAdded,
                //modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_send),
                    contentDescription = "Отправить",
                    tint = Color.White
                )
            }
        }
    }
}