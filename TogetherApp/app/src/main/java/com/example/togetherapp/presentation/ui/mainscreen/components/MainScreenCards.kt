package com.example.togetherapp.presentation.ui.mainscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.togetherapp.R
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.MainScreenState
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreenCards(state: MainScreenState, viewModel: MainScreenViewModel){
    Spacer(modifier = Modifier.height(12.dp))

    CustomHorizontalPager(courses = state.courses)

    Spacer(modifier = Modifier.height(24.dp))

    SectionTitle(title = "Ваши заметки", showAll = true) {
        viewModel.handleEvent(MainScreenEvent.ShowAllNotes)
    }


    Spacer(modifier = Modifier.height(12.dp))

    NoteCard(
        title = "Для создания новой Activity",
        content = "Нужно лишь применить старый дедовский виkkkkkk",
        date = "12 июля"
    )

    Spacer(modifier = Modifier.height(20.dp))

    SectionTitle(title = "Заметки сообщества", showAll = true) {
        viewModel.handleEvent(MainScreenEvent.ShowAllNotes)
    }

    Spacer(modifier = Modifier.height(12.dp))

    state.communityNote?.let { note ->
        CommunityNoteCard(
            userName = "${note.author.name} ${note.author.surname}",
            title = "${note.title} ${note.content[0].text}",
            date = note.date,
            userImageUrl = note.author.avatar
        )
    }
}