package com.example.togetherapp.presentation.ui.cnotedetailsscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.togetherapp.presentation.event.CNoteDetailsScreenEvent
import com.example.togetherapp.presentation.event.CourseDetailsScreenEvent
import com.example.togetherapp.presentation.state.CNoteDetailsScreenState
import com.example.togetherapp.presentation.state.CourseDetailsScreenState
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.ui.components.NoteContentItem
import com.example.togetherapp.presentation.ui.components.NoteTopAppBar
import com.example.togetherapp.presentation.ui.coursedetailsscreen.components.CourseContentItem
import com.example.togetherapp.presentation.ui.coursedetailsscreen.components.CustomTopAppBar
import com.example.togetherapp.presentation.viewmodel.CNoteDetailsScreenViewModel
import com.example.togetherapp.presentation.viewmodel.CourseDetailsScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CNoteDetailsScreenContent(
    viewModel: CNoteDetailsScreenViewModel,
    navController: NavHostController,
    noteId: String
) {
    val state by viewModel.state.observeAsState(CNoteDetailsScreenState())

    LaunchedEffect(noteId) {
        viewModel.handleEvent(CNoteDetailsScreenEvent.LoadCNoteDetails(noteId))
    }

    Scaffold(
        topBar = {
            NoteTopAppBar(
                state = state,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
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
                                CNoteDetailsScreenEvent.LoadCNoteDetails(
                                    noteId
                                )
                            )
                        }
                    )
                }

                state.note != null -> {
                    LazyColumn() {
                        item {
                            Column(
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 20.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .height(36.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFF333333))
                                        .padding(horizontal = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = rememberImagePainter(state.note!!.author.avatar),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .clip(CircleShape)
                                            .padding(end = 4.dp)
                                    )
                                    Text(
                                        text = "${state.note!!.author.name} ${state.note!!.author.surname}",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight(600)
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

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

                        item {
                            Column(
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 20.dp
                                )
                            ) {
                                Spacer(modifier = Modifier.height(20.dp))

                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0x7D7D7777))
                                        .fillMaxWidth()
                                        .height(32.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Комментарии",
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier
                                            .padding(start = 11.dp)
                                    )

                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                state.note?.comments?.forEach { comment ->
                                    CommentItem(comment)
                                }
                            }

                            AddComment(
                                state = state,
                                onCommentTextChanged = { newText ->
                                    viewModel.handleEvent(
                                        CNoteDetailsScreenEvent.UpdateCommentText(
                                            newText
                                        )
                                    )
                                },
                                onCommentAdded = {
                                    viewModel.handleEvent(CNoteDetailsScreenEvent.AddComment(noteId))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}