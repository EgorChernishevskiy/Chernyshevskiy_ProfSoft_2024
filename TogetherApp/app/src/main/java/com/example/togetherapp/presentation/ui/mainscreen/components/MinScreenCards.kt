package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.togetherapp.R
import com.example.togetherapp.presentation.state.MainScreenState

@Composable
fun MainScreenCards(state: MainScreenState){
    Spacer(modifier = Modifier.height(12.dp))

    CustomHorizontalPager(courses = state.courses)

    Spacer(modifier = Modifier.height(24.dp))

    SectionTitle(title = "Ваши заметки", showAll = true)

    Spacer(modifier = Modifier.height(12.dp))

    NoteCard(
        title = "Для создания новой Activity",
        content = "Нужно лишь применить старый дедовский виkkkkkk",
        date = "12 июля"
    )

    Spacer(modifier = Modifier.height(20.dp))

    SectionTitle(title = "Заметки сообщества", showAll = true)

    Spacer(modifier = Modifier.height(12.dp))

    CommunityNoteCard(
        userName = "Имя Фамилия",
        title = "Тест для текста в несколько строк. Это очемн важный момент. Его нужно проверить пост",
        date = "12 июля",
        userImage = painterResource(R.drawable.ic_searchnormal)
    )
}