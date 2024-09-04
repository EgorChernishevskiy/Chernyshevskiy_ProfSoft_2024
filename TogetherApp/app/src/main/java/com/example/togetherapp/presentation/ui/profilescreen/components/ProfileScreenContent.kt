package com.example.togetherapp.presentation.ui.profilescreen.components

import android.os.Build
import android.util.Log
import android.widget.Space
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.togetherapp.R
import com.example.togetherapp.presentation.event.FavoriteScreenEvent
import com.example.togetherapp.presentation.event.ProfileScreenEvent
import com.example.togetherapp.presentation.state.FavoriteScreenState
import com.example.togetherapp.presentation.state.ProfileScreenState
import com.example.togetherapp.presentation.ui.components.BottomNavigationBar
import com.example.togetherapp.presentation.ui.components.CenteredProgressIndicator
import com.example.togetherapp.presentation.ui.components.CustomSearchButton
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.ui.favoritescreen.components.FavoriteCourses
import com.example.togetherapp.presentation.ui.favoritescreen.components.FavoriteLocNotes
import com.example.togetherapp.presentation.ui.favoritescreen.components.FavoriteNotes
import com.example.togetherapp.presentation.ui.mainscreen.components.CommunityNoteCard
import com.example.togetherapp.presentation.ui.mainscreen.components.CourseCard
import com.example.togetherapp.presentation.ui.mainscreen.components.ShowAllTopBar
import com.example.togetherapp.presentation.utils.formatDateProfile
import com.example.togetherapp.presentation.utils.formatPhoneNumber
import com.example.togetherapp.presentation.viewmodel.FavoriteScreenViewModel
import com.example.togetherapp.presentation.viewmodel.ProfileScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    viewModel: ProfileScreenViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.observeAsState(ProfileScreenState())

    LaunchedEffect(Unit) {
        viewModel.handleEvent(ProfileScreenEvent.OnLoadUserProfile)
    }
    Scaffold(
        topBar = {
            if (state.showAllCourses || state.showAllNotes || state.showAllUsers || !state.isMyProfile) {
                val title: String
                if (state.showAllCourses) {
                    title = "Все курсы"
                } else if (state.showAllNotes) {
                    title = "Ваши заметки"
                } else if (!state.isMyProfile) {
                    title = "Профиль"
                } else {
                    title = "Все пользователи"
                }
                ShowAllTopBar(
                    title = title,
                    onHideAllClick = {
                        if (state.showAllCourses) {
                            viewModel.handleEvent(ProfileScreenEvent.HideAllCourses)
                        } else if (state.showAllNotes) {
                            viewModel.handleEvent(ProfileScreenEvent.HideAllNotes)
                        } else if (!state.isMyProfile) {
                            viewModel.handleEvent(ProfileScreenEvent.OnLoadUserProfile)
                            viewModel.handleEvent(ProfileScreenEvent.ShowAllUsers)
                        } else {
                            viewModel.handleEvent(ProfileScreenEvent.HideAllUsers)
                        }
                    }
                )
            } else {
                TopAppBar(
                    title = {
                        Text(
                            text = "Профиль",
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 20.sp
                        )
                    },
                    actions = {
                        if (state.isMyProfile) {ButtonAllUsers { viewModel.handleEvent(ProfileScreenEvent.ShowAllUsers)} }
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
                .padding(top = 0.dp)
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    CenteredProgressIndicator()
                }

                state.error != null -> {
                    ErrorMessage(
                        errorMessage = state.error ?: "Что-то пошло не так",
                        onRetryClick = {
                            viewModel.handleEvent(ProfileScreenEvent.OnErrorClear)
                            viewModel.handleEvent(ProfileScreenEvent.OnLoadUserProfile)
                        }
                    )
                }

                state.showAllCourses -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        state.user?.courses?.let {
                            items(it.size) { index ->
                                val course = state.user!!.courses[index]
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
                }

                state.showAllNotes -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        state.user?.notes?.let {
                            items(it.size) { index ->
                                val note = state.user!!.notes[index]
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
                }

                state.showAllUsers -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        items(state.usersList.size) { index ->
                            val user = state.usersList[index]
                            Spacer(modifier = Modifier.height(20.dp))
                            UserItem(
                                avatar = user.avatar,
                                name = user.name,
                                surname = user.surname,
                                onClick = {
                                    viewModel.handleEvent(
                                        ProfileScreenEvent.OnLoadUserProfileById(
                                            user.id
                                        )
                                    )
                                    viewModel.handleEvent(ProfileScreenEvent.HideAllUsers)

                                }
                            )
                        }
                    }
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    ) {


                        Row {
                            Image(
                                painter = rememberImagePainter(data = state.user?.avatar),
                                contentDescription = "User Avatar",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            Column(
                                modifier = Modifier.padding(bottom = 12.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "${state.user?.name}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "${state.user?.surname}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Дата регистрации: ${formatDateProfile(state.user?.registerDate ?: "")}",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Роль: ${
                                        when (state.user?.role) {
                                            0 -> "Студент"
                                            1 -> "Преподаватель"
                                            2 -> "Админ"
                                            else -> "Неизвестная роль"
                                        }
                                    }",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(0.dp)) {
                            if (state.user?.phone != null) {
                                Text(
                                    text = "Номер телефона: ${formatPhoneNumber(state.user?.phone)}",
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                            }

                            if (state.isMyProfile) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Показывать всем мой номер",
                                        fontSize = 14.sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Switch(
                                        checked = true,
                                        onCheckedChange = { },
                                        colors = SwitchDefaults.colors(
                                            checkedTrackColor = Color.Black,
                                            uncheckedTrackColor = Color(0xFFFFD80C),
                                            checkedThumbColor = Color(0xFFFFD80C),
                                            uncheckedThumbColor = Color(0xFFFFD80C)
                                        ),
                                        modifier = Modifier
                                            .scale(0.7f)
                                            .padding(end = 8.dp)
                                    )
                                }
                            } else {
                                Spacer(Modifier.height(15.dp))
                            }
                        }

                    }
                    LazyColumn(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        state.user?.let {
                            item {
                                FavoriteCourses(
                                    if (state.isMyProfile) "Ваши курсы" else "Курсы",
                                    it.courses,
                                    { viewModel.handleEvent(ProfileScreenEvent.ShowAllCourses) },
                                    navController
                                )
                            }
                            item {
                                it.notes.firstOrNull()?.let { it1 ->
                                    FavoriteNotes(
                                        if (state.isMyProfile) "Ваши заметки" else "Заметки",
                                        it1,
                                        { viewModel.handleEvent(ProfileScreenEvent.ShowAllNotes) },
                                        navController
                                    )
                                }
                            }
                        }

                        if (state.isMyProfile) {
                            item {
                                Button(
                                    onClick = {
                                        viewModel.handleEvent(ProfileScreenEvent.OnLogOut)
                                        navController.navigate("login") {
                                            popUpTo(0) { inclusive = true }
                                            launchSingleTop = true
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFFD80C)
                                    ),
                                    shape = RoundedCornerShape(0.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = "Выйти из аккаунта",
                                        color = Color.Black,
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
