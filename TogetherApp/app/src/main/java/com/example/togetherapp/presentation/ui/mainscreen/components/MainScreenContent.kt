package com.example.togetherapp.presentation.ui.mainscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.MainScreenState
import com.example.togetherapp.presentation.ui.components.CenteredProgressIndicator
import com.example.togetherapp.presentation.ui.components.CustomSearchButton
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    viewModel: MainScreenViewModel,
    navController: NavHostController,
) {

    val state by viewModel.state.observeAsState(MainScreenState())

    LaunchedEffect(Unit) {
        viewModel.handleEvent(MainScreenEvent.LoadCourses)
        viewModel.handleEvent(MainScreenEvent.LoadNotes)
        viewModel.handleEvent(MainScreenEvent.LoadLocalNotes)
    }

    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            if (state.showAllCourses || state.showAllNotes) {
                ShowAllTopBar(
                    title = if (state.showAllCourses) "Все курсы" else "Заметки сообщества",
                    onHideAllClick = {
                        if (state.showAllCourses) {
                            viewModel.handleEvent(MainScreenEvent.HideAllCourses)
                        } else if (state.showAllNotes) {
                            viewModel.handleEvent(MainScreenEvent.HideAllNotes)
                        }
                    }
                )
            } else {
                TopAppBar(
                    title = {
                        if (state.showAllCourses) {
                            ShowAllTopBar(title = "Все курсы") {
                                viewModel.handleEvent(MainScreenEvent.HideAllCourses)
                            }
                        } else if (state.showAllNotes) {
                            ShowAllTopBar(title = "Заметки сообщества") {
                                viewModel.handleEvent(MainScreenEvent.HideAllNotes)
                            }
                        } else if (!isSearching) {
                            Text(
                                text = "Главная",
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 20.sp
                            )
                        } else {
                            TextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                placeholder = {
                                    if (searchQuery.isEmpty()) {
                                        Text(
                                            text = "Поиск",
                                            color = Color(0xFF333333),
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontSize = 14.sp
                                            )
                                        )
                                    }
                                },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_searchnormal),
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .padding(horizontal = 16.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFD6B714)),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    errorIndicatorColor = Color.Transparent
                                ),
                                textStyle = TextStyle(
                                    fontSize = 14.sp
                                ),
                                singleLine = true
                            )
                        }

                    },
                    actions = {
                        if (!isSearching && !state.showAllCourses && !state.showAllNotes) {
                            CustomSearchButton()
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Color(0xFFFFD80C)
                    )
                )
            }
        },

        bottomBar = {
            BottomNavigationBar()
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
                            viewModel.handleEvent(MainScreenEvent.LoadCourses)
                            viewModel.handleEvent(MainScreenEvent.LoadNotes)
                            viewModel.handleEvent(MainScreenEvent.LoadLocalNotes)
                        }
                    )
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

                state.courses.isNotEmpty()  -> {
                    Spacer(modifier = Modifier.height(20.dp))

                    SectionTitle(title = "Ваши курсы", showAll = true) {
                        viewModel.handleEvent(MainScreenEvent.ShowAllCourses)
                    }

                    MainScreenCards(state, viewModel, navController)
                }

                else -> {
                    CenteredProgressIndicator()
                }

//                else -> {
//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    SectionTitle(title = "Ваши курсы", showAll = true) {
//                        viewModel.handleEvent(MainScreenEvent.ShowAllCourses)
//                    }
//
//                    Text(text = "Нет доступных курсов")
//
//                    Button(
//                        modifier = Modifier.align(Center),
//                        onClick = { viewModel.handleEvent(MainScreenEvent.LoadCourses) },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color.Black
//                        )
//                    ) {
//                        Text(
//                            text = "Повторить",
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.White
//                        )
//                    }
//
//                    MainScreenCards(state, viewModel, navController)
//                }
            }

        }
    }
}

//@Preview
//@Composable
//private fun MainScreenPreview() {
//    TogetherAppTheme {
//        MainScreenContent()
//    }
//}