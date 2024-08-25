package com.example.togetherapp.presentation.ui.detailsscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.togetherapp.R
import com.example.togetherapp.domain.model.course.CourseText
import com.example.togetherapp.presentation.event.DetailsScreenEvent
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.DetailsScreenState
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.ui.theme.TogetherAppTheme
import com.example.togetherapp.presentation.viewmodel.DetailsScreenViewModel

//onClick = { navController.popBackStack() }

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenContent(
    viewModel: DetailsScreenViewModel,
    navController: NavHostController,
    courseId: String,
    courseIndex: Int
) {
    val state by viewModel.state.observeAsState(DetailsScreenState())

    LaunchedEffect(courseId) {
        viewModel.handleEvent(DetailsScreenEvent.LoadCourseDetails(courseId))
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                state = state,
                onBackClick = { navController.popBackStack() },
                courseIndex = courseIndex
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
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
                            viewModel.handleEvent(DetailsScreenEvent.LoadCourseDetails(courseId))
                        }
                    )
                }

                state.course != null -> {
                    LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
                        item {
                            Text(
                                text = "Темы",
                                fontWeight = FontWeight(700),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            state.course?.tags?.forEach { tag ->
                                Text(
                                    text = "\u2022 $tag",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight(400),
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    color = Color.Gray
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Занятие",
                                fontWeight = FontWeight(700),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        items(state.course!!.text.size) { index ->
                            CourseContentItem(courseText = state.course!!.text[index])
                        }
                    }
                }
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//private fun MainScreenPreview() {
//    TogetherAppTheme {
//        DetailsScreenContent()
//    }
//}

