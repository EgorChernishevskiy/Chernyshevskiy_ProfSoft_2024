package com.example.togetherapp.presentation.ui.favoritescreen.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.togetherapp.R
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.presentation.event.FavoriteScreenEvent
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.FavoriteScreenState
import com.example.togetherapp.presentation.ui.components.BottomNavigationBar
import com.example.togetherapp.presentation.ui.components.CenteredProgressIndicator
import com.example.togetherapp.presentation.ui.components.CustomSearchButton
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.ui.mainscreen.components.CommunityNoteCard
import com.example.togetherapp.presentation.ui.mainscreen.components.CourseCard
import com.example.togetherapp.presentation.ui.mainscreen.components.MainScreenCards
import com.example.togetherapp.presentation.ui.mainscreen.components.NoteCard
import com.example.togetherapp.presentation.ui.mainscreen.components.ShowAllTopBar
import com.example.togetherapp.presentation.viewmodel.FavoriteScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreenContent(
    viewModel: FavoriteScreenViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.observeAsState(FavoriteScreenState())

    LaunchedEffect(Unit) {
        viewModel.handleEvent(FavoriteScreenEvent.LoadCourses)
        viewModel.handleEvent(FavoriteScreenEvent.LoadNotes)
        viewModel.handleEvent(FavoriteScreenEvent.LoadLocalNotes)
    }

    Scaffold(
        topBar = {
            if (state.showAllCourses || state.showAllNotes || state.showAllLocalNotes) {
                val title: String
                if (state.showAllCourses) {
                    title = "Все курсы"
                } else if (state.showAllLocalNotes) {
                    title = "Ваши заметки"
                } else {
                    title = "Заметки сообщества"
                }
                ShowAllTopBar(
                    title = title,
                    onHideAllClick = {
                        if (state.showAllCourses) {
                            viewModel.handleEvent(FavoriteScreenEvent.HideAllCourses)
                        } else if (state.showAllNotes) {
                            viewModel.handleEvent(FavoriteScreenEvent.HideAllNotes)
                        } else {
                            viewModel.handleEvent(FavoriteScreenEvent.HideAllLocalNotes)
                        }
                    }
                )
            } else {
                TopAppBar(
                    title = {
                        Text(
                            text = "Избранное",
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 20.sp
                        )
                    },
                    actions = {
                        CustomSearchButton()
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(0xFFFFD80C)
                    )
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            when {
                state.isLoading -> {
                    CenteredProgressIndicator()
                }

                state.error != null -> {

                    ErrorMessage(
                        errorMessage = state.error ?: "Что-то пошло не так",
                        onRetryClick = {
                            viewModel.handleEvent(FavoriteScreenEvent.LoadCourses)
                            viewModel.handleEvent(FavoriteScreenEvent.LoadNotes)
                            viewModel.handleEvent(FavoriteScreenEvent.LoadLocalNotes)
                        }
                    )

                }

                (state.courses.isEmpty() && state.localNote == null && state.communityNote == null) -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Пусто",
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }

                state.showAllCourses -> {
                    LazyColumn {
                        items(state.courses.size) { index ->
                            val course = state.courses[index]
                            Spacer(modifier = Modifier.height(20.dp))
                            CourseCard(
                                title = course.title,
                                tags = course.tags,
                                onClick = {
                                    navController.navigate("details/${course.id}/${index}")
                                }
                            )
                        }
                    }
                }

                state.showAllNotes -> {
                    LazyColumn {
                        items(state.notes.size) { index ->
                            val note = state.notes[index]
                            Spacer(modifier = Modifier.height(20.dp))
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
                }

                state.showAllLocalNotes -> {
                    LazyColumn {
                        items(state.localNotes.size) { index ->
                            val note = state.localNotes[index]
                            Spacer(modifier = Modifier.height(20.dp))
                            NoteCard(
                                title = note.title,
                                content = note.content[0].text,
                                date = note.date,
                                onClick = {
                                    navController.navigate("lnote/${note.id}")
                                }
                            )
                        }
                    }
                }

                else -> {
                    FavoriteCourses(state, viewModel, navController)
                    FavoriteLocNotes(state, viewModel, navController)
                    FavoriteNotes(state, viewModel, navController)
                }
            }

        }
    }
}