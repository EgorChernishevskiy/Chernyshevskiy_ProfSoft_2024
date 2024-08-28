package com.example.togetherapp.presentation.ui.createnotescreen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.togetherapp.R
import com.example.togetherapp.presentation.event.CreateNoteScreenEvent
import com.example.togetherapp.presentation.state.CreateNoteScreenState
import com.example.togetherapp.presentation.ui.components.BottomNavigationBar

import com.example.togetherapp.presentation.ui.components.IconButtonBack
import com.example.togetherapp.presentation.viewmodel.CreateNoteViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CreateNoteScreenContent(
    viewModel: CreateNoteViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.observeAsState(CreateNoteScreenState())

    Scaffold(
        topBar = {
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
                    IconButtonBack { navController.popBackStack() }
                    Text(
                        text = "Новая заметка",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 20.sp
                    )
                }
            }
        },

        bottomBar = {
            if (!state.whatToAdd && !state.addPhoto && !state.addText) {
                BottomNavigationBar(navController)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .height(40.dp) // Устанавливаем высоту панели
                    .fillMaxWidth()
                    .background(Color(0x7D7D7777), shape = RoundedCornerShape(8.dp)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Первый кликабельный элемент
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .padding(start = 2.dp)
                        .background(
                            if (state.isLocal) Color(0xFFFFD80C) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable{
                            viewModel.handleEvent(CreateNoteScreenEvent.OnLocalSelected)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Локально",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }

                // Второй кликабельный элемент
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .padding(horizontal = 2.dp)
                        .background(
                            if (!state.isLocal) Color(0xFFFFD80C) else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            viewModel.handleEvent(CreateNoteScreenEvent.OnCommunitySelected)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Сообщество",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(33.dp)
                        .background(Color(0x7D7D7777))
                )

                Spacer(modifier = Modifier.width(8.dp)) // Отступ между полоской и заголовком

                // Заголовок
                Text(
                    text = "Название",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Описание заметки",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.weight(1f))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF333333), shape = RoundedCornerShape(8.dp))
                        .clickable {
                            // TODO: Handle Add click
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add Icon",
                        modifier = Modifier.size(20.dp)
                    )
                }

                Button(
                    onClick = { /* TODO: Handle Done click */ },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .padding(start = 12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF333333)
                    )
                ) {
                    Text(text = "Готово", color = Color.White)
                }
            }
        }
    }
}