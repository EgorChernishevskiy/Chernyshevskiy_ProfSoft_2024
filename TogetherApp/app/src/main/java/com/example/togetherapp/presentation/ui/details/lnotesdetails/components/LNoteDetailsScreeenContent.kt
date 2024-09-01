package com.example.togetherapp.presentation.ui.details.lnotesdetails.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.event.CNoteDetailsScreenEvent
import com.example.togetherapp.presentation.event.LNoteDetailsScreenEvent
import com.example.togetherapp.presentation.state.note.LNoteDetailsScreenState
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.ui.details.components.NoteContentItem
import com.example.togetherapp.presentation.ui.details.components.NoteTopAppBar
import com.example.togetherapp.presentation.viewmodel.LNoteDetailsScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LNoteDetailsScreenContent(
    viewModel: LNoteDetailsScreenViewModel,
    navController: NavHostController,
    noteId: Int
) {
    val state by viewModel.state.observeAsState(LNoteDetailsScreenState())

    LaunchedEffect(noteId) {
        viewModel.handleEvent(LNoteDetailsScreenEvent.LoadLNoteDetails(noteId))
        viewModel.handleEvent(LNoteDetailsScreenEvent.CheckIfFavorite(noteId))
    }

    Scaffold(
        topBar = {
            NoteTopAppBar(
                state = state,
                onBackClick = { navController.popBackStack() },
                onFavoriteClick = {
                    viewModel.handleEvent(
                        if (state.isFavorite) LNoteDetailsScreenEvent.RemoveFromFavorite
                        else LNoteDetailsScreenEvent.AddToFavorite
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when {
                state.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    ErrorMessage(
                        errorMessage = state.error ?: "Что-то пошло не так",
                        onRetryClick = {
                            viewModel.handleEvent(
                                LNoteDetailsScreenEvent.LoadLNoteDetails(
                                    noteId
                                )
                            )
                        }
                    )
                }

                state.note != null -> {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        item {
                            Column(
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 20.dp
                                )
                            ) {
                                Text(
                                    text = "Текст",
                                    fontWeight = FontWeight(700),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }

                        items(state.note!!.content.size) { index ->
                            NoteContentItem(noteContent = state.note!!.content[index])
                        }
                    }
                }
            }
        }
    }
}