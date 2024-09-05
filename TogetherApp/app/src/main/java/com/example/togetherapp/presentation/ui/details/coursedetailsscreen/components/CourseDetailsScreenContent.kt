package com.example.togetherapp.presentation.ui.details.coursedetailsscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.togetherapp.R
import com.example.togetherapp.presentation.event.CourseDetailsScreenEvent
import com.example.togetherapp.presentation.state.CourseDetailsScreenState
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.viewmodel.details.CourseDetailsScreenViewModel
import org.koin.androidx.compose.koinViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreenContent(
    navController: NavHostController,
    courseId: String,
    courseIndex: Int
) {
    val viewModel: CourseDetailsScreenViewModel = koinViewModel()
    val state by viewModel.state.observeAsState(CourseDetailsScreenState())

    LaunchedEffect(courseId) {
        viewModel.handleEvent(CourseDetailsScreenEvent.LoadCourseDetails(courseId))
        viewModel.handleEvent(CourseDetailsScreenEvent.CheckIfFavorite(courseId))
    }

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CustomTopAppBar(
                    state = state,
                    onBackClick = { navController.popBackStack() },
                    courseIndex = courseIndex,
                    onFavoriteClick = {
                        viewModel.handleEvent(
                            if (state.isFavorite) CourseDetailsScreenEvent.RemoveFromFavorite
                            else CourseDetailsScreenEvent.AddToFavorite
                        )
                    }
                )
            }

            when {
                state.isLoading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                state.error != null -> {
                    item {
                        ErrorMessage(
                            errorMessage = state.error
                                ?: stringResource(R.string.default_error_message),
                            onRetryClick = {
                                viewModel.handleEvent(CourseDetailsScreenEvent.OnErrorClear)
                                viewModel.handleEvent(
                                    CourseDetailsScreenEvent.LoadCourseDetails(
                                        courseId
                                    )
                                )
                            }
                        )
                    }
                }

                state.course != null -> {
                    item {
                        Column(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 20.dp
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.course_themes_label),
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
                                text = stringResource(R.string.course_class_text_label),
                                fontWeight = FontWeight(700),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    items(state.course!!.text.size) { index ->
                        CourseContentItem(courseText = state.course!!.text[index])
                    }
                }
            }
        }
    }
}

