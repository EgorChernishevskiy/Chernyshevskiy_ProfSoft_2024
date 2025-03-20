package com.example.togetherapp.presentation.ui.mainscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.togetherapp.R
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.togetherapp.domain.utils.NoteTopic
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.MainScreenState
import com.example.togetherapp.presentation.ui.components.CenteredProgressIndicator
import com.example.togetherapp.presentation.ui.components.CustomSearchButton
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.utils.getTopicName
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    navController: NavHostController,
) {
    val viewModel: MainScreenViewModel = koinViewModel()
    val state by viewModel.state.observeAsState(MainScreenState())
    var expanded by remember { mutableStateOf(false) } // Состояние для управления видимостью меню

    LaunchedEffect(Unit) {
        viewModel.handleEvent(MainScreenEvent.LoadChallenges)
        //viewModel.handleEvent(MainScreenEvent.LoadCourses)
        viewModel.handleEvent(MainScreenEvent.LoadNotes)
        viewModel.handleEvent(MainScreenEvent.LoadLocalNotes)
    }

    Scaffold(
        topBar = {
            if (state.showAllCourses || state.showAllNotes || state.showAllLocalNotes) {
                val title: String
                if (state.showAllCourses) {
                    title = stringResource(R.string.all_courses_top_bar_label)
                } else if (state.showAllLocalNotes) {
                    title = stringResource(R.string.your_notes_top_bar_label)
                } else {
                    title = stringResource(R.string.com_notes_text_label)
                }
                ShowAllTopBar(
                    title = title,
                    onHideAllClick = {
                        if (state.showAllCourses) {
                            viewModel.handleEvent(MainScreenEvent.HideAllCourses)
                        } else if (state.showAllNotes) {
                            viewModel.handleEvent(MainScreenEvent.HideAllNotes)
                        } else {
                            viewModel.handleEvent(MainScreenEvent.HideAllLocalNotes)
                        }
                    }
                )
            } else {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.home_page_title_label),
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 20.sp
                        )
                    },
                    actions = {
                        if (!state.showAllCourses && !state.showAllNotes && !state.showAllLocalNotes) {
                            //CustomSearchButton()
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(0xFFFFD80C)
                    )
                )
            }
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
                    if (state.error!!.endsWith("Unauthorized")) {
                        if (!state.isNavigatedToLogin) {
                            viewModel.handleEvent(MainScreenEvent.NavigateToLogin)
                            viewModel.handleEvent(MainScreenEvent.OnResetState)
                            navController.navigate("login") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    } else {
                        ErrorMessage(
                            errorMessage = state.error
                                ?: stringResource(R.string.default_error_message),
                            onRetryClick = {
                                viewModel.handleEvent(MainScreenEvent.OnErrorClear)
                                viewModel.handleEvent(MainScreenEvent.LoadCourses)
                                viewModel.handleEvent(MainScreenEvent.LoadNotes)
                                viewModel.handleEvent(MainScreenEvent.LoadLocalNotes)
                            }
                        )
                    }
                }

                state.showAllCourses -> {
                    // Выпадающее меню для выбора темы
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.TopStart)
                    ) {
                        Text(
                            text = "Тема: ${state.topicName ?: "Все"}",
                            modifier = Modifier
                                .clickable { expanded = true }
                                .padding(8.dp)
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Все") },
                                onClick = {
                                    viewModel.handleEvent(MainScreenEvent.ShowAllCourses)
                                    viewModel.handleEvent(MainScreenEvent.UpdateTopicName( "Все"))
                                    expanded = false
                                }
                            )
                            NoteTopic.entries.forEach { topic ->
                                DropdownMenuItem(
                                    text = { Text(getTopicName(topic)) },
                                    onClick = {
                                        viewModel.handleEvent(MainScreenEvent.LoadChallengesByTopic(topic)) // Загружаем заметки по теме
                                        viewModel.handleEvent(MainScreenEvent.UpdateTopicName(getTopicName(topic))) // Обновляем тему
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    LazyColumn {
                        items(state.challenges.size) { index ->
                            val challenge = state.challenges[index]
                            Spacer(modifier = Modifier.height(20.dp))
                            ChallengeCard(
                                technique = challenge.technique,
                                theme = challenge.theme,
                                palette = challenge.palette,
                                onClick = {
                                    navController.navigate("challenge/${challenge.id}")
                                }
                            )
                        }
                    }
                }

                state.showAllNotes -> {
                    // Выпадающее меню для выбора темы
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.TopStart)
                    ) {
                        Text(
                            text = "Тема: ${state.topicName ?: "Все"}",
                            modifier = Modifier
                                .clickable { expanded = true }
                                .padding(8.dp)
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Все") },
                                onClick = {
                                    viewModel.handleEvent(MainScreenEvent.ShowAllNotes) // Загружаем все заметки
                                    viewModel.handleEvent(MainScreenEvent.UpdateTopicName( "Все")) // Сбрасываем тему
                                    expanded = false
                                }
                            )
                            NoteTopic.entries.forEach { topic ->
                                DropdownMenuItem(
                                    text = { Text(getTopicName(topic)) },
                                    onClick = {
                                        viewModel.handleEvent(MainScreenEvent.LoadNotesByTopic(topic)) // Загружаем заметки по теме
                                        viewModel.handleEvent(MainScreenEvent.UpdateTopicName(getTopicName(topic))) // Обновляем тему
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
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
                }

                state.challenges.isNotEmpty() || state.localNote != null || state.communityNote != null -> {
                    MainScreenCards(state, viewModel, navController)
                }

                else -> {
                    CenteredProgressIndicator()
                }
            }

        }
    }
}