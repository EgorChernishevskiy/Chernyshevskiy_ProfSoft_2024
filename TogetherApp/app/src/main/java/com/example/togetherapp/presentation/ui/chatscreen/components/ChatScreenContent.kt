package com.example.togetherapp.presentation.ui.chatscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.togetherapp.R
import com.example.togetherapp.presentation.event.ChatScreenEvent
import com.example.togetherapp.presentation.state.ChatScreenState
import com.example.togetherapp.presentation.ui.components.CustomSearchButton
import com.example.togetherapp.presentation.ui.components.ErrorMessage
import com.example.togetherapp.presentation.viewmodel.ChatScreenViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenContent() {

    val viewModel: ChatScreenViewModel = koinViewModel()
    val state by viewModel.state.observeAsState(ChatScreenState())
    var showTopicDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.handleEvent(ChatScreenEvent.LoadMessages)
        viewModel.handleEvent(ChatScreenEvent.GetCurrentUserId)
        viewModel.subscribeToSSE()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.unsubscribeFromSSE() // Отписываемся от SSE
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.chat_top_bar_label),
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 20.sp
                    )
                },
                actions = {
                    IconButton(onClick = { showTopicDialog = true }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_topic),
                            contentDescription = "Select Topic"
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFFFD80C)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (state.error != null) {
                ErrorMessage(
                    errorMessage = state.error ?: stringResource(R.string.default_error_message),
                    onRetryClick = {
                        viewModel.handleEvent(ChatScreenEvent.OnErrorClear)
                        viewModel.handleEvent(ChatScreenEvent.LoadMessages)
                        viewModel.handleEvent(ChatScreenEvent.GetCurrentUserId)
                    }
                )
            } else {
                val sortedMessages = state.messages.sortedBy { it.timestamp }.asReversed()
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    reverseLayout = true
                ) {
                    items(sortedMessages) { message ->
                        ChatMessageItem(
                            message = message,
                            isCurrentUser = message.sender == state.currentUserId
                        )
                    }
                }

                ChatInput(onSendClicked = { messageText ->
                    viewModel.handleEvent(ChatScreenEvent.SendMessage(messageText))
                },
                    onRefreshClicked = {
                        viewModel.handleEvent(ChatScreenEvent.RefreshMessages)
                    }
                )

                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }
    }

    if (showTopicDialog) {
        TopicSelectionDialog(
            onTopicSelected = { topic ->
                viewModel.setCurrentTopic(topic)
                showTopicDialog = false
            },
            onDismiss = { showTopicDialog = false }
        )
    }
}