package com.example.togetherapp.presentation.ui.createnotescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.togetherapp.R
import com.example.togetherapp.presentation.event.CreateNoteScreenEvent
import com.example.togetherapp.presentation.state.CreateNoteScreenState
import com.example.togetherapp.presentation.ui.components.BottomNavigationBar
import com.example.togetherapp.presentation.ui.components.IconButtonBack
import com.example.togetherapp.presentation.ui.details.components.NoteContentItem
import com.example.togetherapp.presentation.viewmodel.CreateNoteViewModel


@Composable
fun CreateNoteScreenContent(
    viewModel: CreateNoteViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.observeAsState(CreateNoteScreenState())
    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFFFD80C)),
                ) {
                    Row(
                        modifier = Modifier.padding(top = 8.dp, end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButtonBack {
                            viewModel.handleEvent(CreateNoteScreenEvent.OnResetState)
                            navController.popBackStack()
                        }
                        Text(
                            text = "Новая заметка",
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 20.sp
                        )
                    }
                }
            },

            bottomBar = {
                if (!state.whatToAdd && !state.addPhoto && !state.addText) {
                    BottomNavigationBar(navController)
                }
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .background(Color(0x7D7D7777), shape = RoundedCornerShape(8.dp)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp)
                            .padding(start = 2.dp)
                            .background(
                                if (state.isLocal) Color(0xFFFFD80C) else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                viewModel.handleEvent(CreateNoteScreenEvent.OnLocalSelected)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Локально",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp)
                            .padding(horizontal = 2.dp)
                            .background(
                                if (!state.isLocal) Color(0xFFFFD80C) else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                viewModel.handleEvent(CreateNoteScreenEvent.OnCommunitySelected)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Сообщество",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(2.dp)
                            .height(33.dp)
                            .background(Color(0x7D7D7777))
                    )

                    Spacer(modifier = Modifier.width(8.dp))


                    BasicTextField(
                        value = state.title,
                        onValueChange = {
                            viewModel.handleEvent(
                                CreateNoteScreenEvent.OnTitleChange(
                                    it
                                )
                            )
                        },
                        textStyle = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (state.isLocal) {
                    state.localNote?.let { localNote ->
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(localNote.content.size) { index ->
                                NoteContentItem(noteContent = localNote.content[index])
                            }
                        }
                    } ?: run {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                } else {
                    state.communityNote?.let { communityNote ->
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(communityNote.content.size) { index ->
                                NoteContentItem(noteContent = communityNote.content[index])
                            }
                        }
                    } ?: run {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFF333333), shape = RoundedCornerShape(8.dp))
                            .clickable {
                                viewModel.handleEvent(CreateNoteScreenEvent.OnWhatToAdd)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Add Icon",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Button(
                        onClick = {
                            if (state.isLocal) {
                                viewModel.handleEvent(CreateNoteScreenEvent.OnLocalCreated)
                            } else {
                                viewModel.handleEvent(CreateNoteScreenEvent.OnCommunityCreated)
                            }
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .padding(start = 12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF333333)
                        )
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(30.dp),
                                color = Color(0xFFD6B714)
                            )
                        } else {
                            Text(text = "Готово", color = Color.White)
                        }
                    }
                }
            }
        }

        if (state.whatToAdd) {
            MyBottomSheet(
                onDismiss = {
                    viewModel.handleEvent(CreateNoteScreenEvent.OnDismissWhatToAdd)
                },
                onTextSelected = {
                    viewModel.handleEvent(CreateNoteScreenEvent.OnShowAddText)
                },
                onPhotoLinkSelected = {
                    viewModel.handleEvent(CreateNoteScreenEvent.OnShowAddPhoto)
                }
            )
        }
        state.error?.let {
            ErrorDialog(OnRetry = {
                if (state.isLocal) {
                    viewModel.handleEvent(CreateNoteScreenEvent.OnLocalCreated)
                } else {
                    viewModel.handleEvent(CreateNoteScreenEvent.OnCommunityCreated)
                }
            })
        }

        if (state.addPhoto || state.addText) {
            viewModel.handleEvent(CreateNoteScreenEvent.OnDismissWhatToAdd)
            state.addedItem?.let {
                AddItemDialog(
                    title = if (state.addPhoto) "Добавить фото" else "Добавить текст",
                    placeholder = if (state.addPhoto) "http://..." else "Введите текст...",
                    inputText = it,
                    onInputChange = { viewModel.handleEvent(CreateNoteScreenEvent.OnAddItemChange(it)) },
                    onCancel = {
                        viewModel.handleEvent(CreateNoteScreenEvent.OnDismissAddItem)
                    },
                    onAdd = {
                        if (state.addPhoto) {
                            viewModel.handleEvent(CreateNoteScreenEvent.OnAddPhoto)
                            viewModel.handleEvent(CreateNoteScreenEvent.OnDismissAddItem)
                        } else if (state.addText) {
                            viewModel.handleEvent(CreateNoteScreenEvent.OnAddText)
                            viewModel.handleEvent(CreateNoteScreenEvent.OnDismissAddItem)
                        }
                    }
                )
            }
        }
    }
}
