package com.example.togetherapp.presentation.ui.favoritescreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.presentation.ui.mainscreen.components.CommunityNoteCard
import com.example.togetherapp.presentation.ui.mainscreen.components.SectionTitle
import com.example.togetherapp.presentation.ui.navigation.Routes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoriteNotes(
    title: String,
    note: Note?,
    onShowAllClick: () -> Unit,
    navController: NavHostController
) {
    Spacer(modifier = Modifier.height(20.dp))

    SectionTitle(title = title, showAll = true) {
        onShowAllClick()
    }

    Spacer(modifier = Modifier.height(12.dp))


    if (note != null) {
        CommunityNoteCard(
            userName = "${note.author.name} ${note.author.surname}",
            title = "${note.title} ${note.content[0].text}",
            date = note.date,
            userImageUrl = note.author.avatar,
            onClick = {
                navController.navigate(
                    Routes.CNoteDetail.replace(
                        "{noteId}",
                        note.id
                    )
                )
            }
        )
    }

}