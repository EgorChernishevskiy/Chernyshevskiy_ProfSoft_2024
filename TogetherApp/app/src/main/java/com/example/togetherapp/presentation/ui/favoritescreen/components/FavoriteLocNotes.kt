package com.example.togetherapp.presentation.ui.favoritescreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.event.FavoriteScreenEvent
import com.example.togetherapp.presentation.state.FavoriteScreenState
import com.example.togetherapp.presentation.ui.mainscreen.components.NoteCard
import com.example.togetherapp.presentation.ui.mainscreen.components.SectionTitle
import com.example.togetherapp.presentation.viewmodel.FavoriteScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoriteLocNotes(state: FavoriteScreenState, viewModel: FavoriteScreenViewModel, navController: NavHostController){
    Spacer(modifier = Modifier.height(24.dp))

    SectionTitle(title = "Ваши заметки", showAll = true) {
        viewModel.handleEvent(FavoriteScreenEvent.ShowAllLocalNotes)
    }

    Spacer(modifier = Modifier.height(12.dp))

    state.localNote?.let { note ->
        note.content[0].text?.let {
            NoteCard(
                title = note.title,
                content = it,
                date = note.date,
                onClick = {
                    navController.navigate("lnote/${note.id}")
                }
            )
        }
    }
}