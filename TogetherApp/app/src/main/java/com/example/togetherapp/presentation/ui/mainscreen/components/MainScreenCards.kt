package com.example.togetherapp.presentation.ui.mainscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.MainScreenState
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreenCards(state: MainScreenState, viewModel: MainScreenViewModel, navController: NavHostController){

    Spacer(modifier = Modifier.height(20.dp))

    SectionTitle(title = "Ваши курсы", showAll = true) {
        viewModel.handleEvent(MainScreenEvent.ShowAllCourses)
    }
    Spacer(modifier = Modifier.height(12.dp))

    CustomHorizontalPager(courses = state.courses, navController)

    Spacer(modifier = Modifier.height(24.dp))

    SectionTitle(title = "Ваши заметки", showAll = true) {
        viewModel.handleEvent(MainScreenEvent.ShowAllLocalNotes)
    }

    Spacer(modifier = Modifier.height(12.dp))

    state.localNote?.let { note ->
        NoteCard(
            title = note.title,
            content = note.content[0].text,
            date = note.date,
            onClick = {
                navController.navigate("lnote/${note.id}")
            }
        )
    }

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
            userImageUrl = note.author.avatar,
            onClick = {
                navController.navigate("cnote/${note.id}")
            }
        )
    }
}