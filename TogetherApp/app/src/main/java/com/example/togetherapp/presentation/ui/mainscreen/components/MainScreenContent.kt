package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togetherapp.R
import com.example.togetherapp.presentation.ui.splashscreen.components.SplashScreenContent
import com.example.togetherapp.presentation.ui.theme.TogetherAppTheme

data class Course(val title: String, val tags: List<String>)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Главная",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 20.sp
                    )
                },

                actions = {
                    IconButton(
                        onClick = { /* Search action */ },
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFD6B714))
                            .width(36.dp)
                            .height(36.dp),
                    )
                    {
                        Icon(
                            painter = painterResource(R.drawable.ic_searchnormal),
                            contentDescription = null,
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFFFD80C)
                )
            )
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

            Spacer(modifier = Modifier.height(20.dp))

            SectionTitle(title = "Ваши курсы", showAll = true)

            Spacer(modifier = Modifier.height(12.dp))

//            CourseCard(
//                title = "Основы Андроида",
//                tags = listOf(
//                    "View",
//                    "Компоненты андроид",
//                    "Создание проекта",
//                    "Intent",
//                    "Manifest"
//                )
//            )

            HorizontalCourseList(
                courses = listOf(
                    Course("Основы Андроида", listOf("View", "Компоненты андроид", "Создание проекта", "Intent", "Manifest")),
                    Course("Продвинутый Android", listOf("Jetpack Compose", "Coroutines", "Hilt")),
                    Course("Кроссплатформенная разработка", listOf("Flutter", "Kotlin Multiplatform", "React Native"))
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle(title = "Ваши заметки", showAll = true)

            Spacer(modifier = Modifier.height(12.dp))

            NoteCard(
                title = "Для создания новой Activity",
                content = "Нужно лишь применить старый дедовский ви...",
                date = "12 июля"
            )

            Spacer(modifier = Modifier.height(20.dp))

            SectionTitle(title = "Заметки сообщества", showAll = true)

            Spacer(modifier = Modifier.height(12.dp))

            CommunityNoteCard(
                userName = "Имя Фамилия",
                title = "Тест для текста в несколько строк. Это оче...",
                content = "н важный момент. Его нужно проверить пост...",
                date = "12 июля"
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    Surface(
        modifier = Modifier.height(56.dp),
        color = Color.White,
    ) {
        Column {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = Color.Gray
            )
            NavigationBar(
                contentColor = Color.Black
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_home),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = true,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7)
                    ),
                    onClick = { /* Handle home click */ }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_favourite),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = false,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7)
                    ),
                    onClick = { /* Handle favorite click */ }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_add_note),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = false,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7)
                    ),
                    onClick = { /* Handle add click */ }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_chat),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = false,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7)
                    ),
                    onClick = { /* Handle notifications click */ }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_nav_profile),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    },
                    selected = false,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0x66D7D7D7)
                    ),
                    onClick = { /* Handle profile click */ }
                )
            }
        }
    }
}

@Composable
fun SectionTitle(title: String, showAll: Boolean) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x7D7D7777))
            .fillMaxWidth()
            .height(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 11.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Spacer(
            modifier = Modifier
                .background(Color.White)
                .width(2.dp)
                .height(32.dp)
        )

        if (showAll) {
            TextButton(onClick = { /* Show all action */ }) {
                Text(
                    text = "Все",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF646464)
                )
            }
        }
    }
}

@Composable
fun HorizontalCourseList(courses: List<Course>) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 8.dp) // Отступ для всего списка
    ) {
        items(courses) { course ->
            CourseCard(title = course.title, tags = course.tags)
        }
    }
}
@Composable
fun CourseCard(title: String, tags: List<String>) {
    Card(
        modifier = Modifier
            .width(200.dp) // Установите ширину карточки
            .padding(end = 8.dp), // Отступ между карточками
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFD80C)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            WrapContent(tags)
        }
    }
}

@Composable
fun WrapContent(tags: List<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tags.forEach { tag ->
            Text(text = tag)
        }
    }
}

@Composable
fun NoteCard(title: String, content: String, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFD80C)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = date, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun CommunityNoteCard(userName: String, title: String, content: String, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFD80C)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = userName, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = date, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    TogetherAppTheme {
        MainScreenContent()
    }
}