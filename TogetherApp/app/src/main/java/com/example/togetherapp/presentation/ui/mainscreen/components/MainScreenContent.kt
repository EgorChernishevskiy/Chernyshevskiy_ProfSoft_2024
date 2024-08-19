package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togetherapp.presentation.ui.splashscreen.components.SplashScreenContent
import com.example.togetherapp.presentation.ui.theme.TogetherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Главная") },
                actions = {
                    IconButton(onClick = { /* Search action */ }) {
                        Icon(Icons.Default.Search, contentDescription = null)
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
        Column(modifier = Modifier.padding(paddingValues)) {
            SectionTitle(title = "Ваши курсы", showAll = true)
            CourseCard(title = "Основы Андроида", tags = listOf("View", "Компоненты андроид", "Создание проекта", "Intent", "Manifest"))
            SectionTitle(title = "Ваши заметки", showAll = true)
            NoteCard(title = "Для создания новой Activity", content = "Нужно лишь применить старый дедовский ви...", date = "12 июля")
            SectionTitle(title = "Заметки сообщества", showAll = true)
            CommunityNoteCard(userName = "Имя Фамилия", title = "Тест для текста в несколько строк. Это оче...", content = "н важный момент. Его нужно проверить пост...", date = "12 июля")
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            selected = true,
            onClick = { /* Handle home click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
            selected = false,
            onClick = { /* Handle favorite click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = null) },
            selected = false,
            onClick = { /* Handle add click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
            selected = false,
            onClick = { /* Handle notifications click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            selected = false,
            onClick = { /* Handle profile click */ }
        )
    }
}

@Composable
fun SectionTitle(title: String, showAll: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        if (showAll) {
            TextButton(onClick = { /* Show all action */ }) {
                Text(text = "Все")
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CourseCard(title: String, tags: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFD80C)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(8.dp))
            WrapContent(tags)
        }
    }
}

@Composable
fun WrapContent(tags: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tags.forEach { tag ->
            Surface(
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = tag,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun NoteCard(title: String, content: String, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFD80C)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
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
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFD80C)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = userName, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
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