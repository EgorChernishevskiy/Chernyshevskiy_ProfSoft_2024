package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togetherapp.R
import com.example.togetherapp.domain.model.Course
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.MainScreenState
import com.example.togetherapp.presentation.ui.components.CenteredProgressIndicator
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.ui.splashscreen.components.SplashScreenContent
import com.example.togetherapp.presentation.ui.theme.TogetherAppTheme
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    viewModel: MainScreenViewModel
) {

    val state by viewModel.state.observeAsState(MainScreenState())

    LaunchedEffect(Unit) {
        viewModel.handleEvent(MainScreenEvent.LoadCourses)
    }

    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (!isSearching) {
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
                                        style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp)
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
                    if (!isSearching) {
                        IconButton(
                            onClick = { isSearching = true },
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFD6B714))
                                .width(36.dp)
                                .height(36.dp),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_searchnormal),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(16.dp)
                            )
                        }
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
            when {
                state.isLoading -> {
                    CenteredProgressIndicator()
                }
                state.error != null -> {
                    ErrorMessage(
                        errorMessage = state.error ?: "Что-то пошло не так",
                        onRetryClick = { viewModel.handleEvent(MainScreenEvent.LoadCourses) }
                    )
                }
                state.courses.isNotEmpty() -> {
                    YourCoursesTitle()

                    MainScreenCards(state)
                }
                else -> {
                    YourCoursesTitle()

                    Text(text = "Нет доступных курсов")

                    MainScreenCards(state)
                }
            }

        }
    }
}

@Composable
fun BottomNavigationBar() {
    Surface(
        color = Color.White,
        modifier = Modifier.height(56.dp)
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
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
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
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
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
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
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
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
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
                        indicatorColor = Color(0x66D7D7D7),
                        selectedIconColor = Color.Black
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomHorizontalPager(courses: List<Course>) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        val pagerState = rememberPagerState(
            pageCount = { courses.size }
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(end = 12.dp)
            ) { page ->
                val course = courses[page]
                CourseCard(
                    title = course.title,
                    tags = course.tags
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, end = 16.dp)
                    .height(4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(courses.size) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .height(4.dp)
                            .background(
                                color = if (pagerState.currentPage == index) Color.Black else Color.LightGray
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun CourseCard(title: String, tags: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(end = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFD80C)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            title.split(" ").forEach { word ->
                Text(
                    text = word,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            WrapContent(tags)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WrapContent(tags: List<String>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        tags.forEach { tag ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF333333),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = tag,
                    style = MaterialTheme.typography.bodySmall.copy()
                )
            }
        }
    }
}

@Composable
fun NoteCard(title: String, content: String, date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFD80C)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF806B00),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Row(
            modifier = Modifier
                .width(71.dp)
                .height(30.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF333333)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = date,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Composable
fun CustomTitleText(title: String) {
    val words = title.split(" ")
    val firstLine = StringBuilder()
    val secondLine = StringBuilder()
    val thirdLine = StringBuilder()

    for (word in words) {
        when {
            firstLine.isEmpty() || firstLine.length + word.length <= 20 -> firstLine.append("$word ")
            secondLine.isEmpty() || secondLine.length + word.length <= 20 -> secondLine.append("$word ")
            else -> thirdLine.append("$word ")
        }
    }

    Column {
        Text(
            text = firstLine.toString().trim(),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = secondLine.toString().trim(),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = thirdLine.toString().trim(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF806B00)
        )
    }
}

@Composable
fun CommunityNoteCard(userName: String, title: String, userImage: Painter, date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFD80C)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(top = 26.dp, start = 12.dp, end = 12.dp, bottom = 10.dp)) {
                CustomTitleText(title = title)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF333333))
                    .height(30.dp)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = userImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(14.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = userName,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF333333))
                    .height(30.dp)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = date,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
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