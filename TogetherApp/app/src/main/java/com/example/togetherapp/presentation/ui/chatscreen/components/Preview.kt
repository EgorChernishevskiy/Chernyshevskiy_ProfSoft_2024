//package com.example.togetherapp.presentation.ui.chatscreen.components
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.togetherapp.domain.model.chat.ChatMessage
//import com.example.togetherapp.domain.model.comnote.Author
//import com.example.togetherapp.presentation.ui.chatscreen.ChatScreen
//import com.example.togetherapp.presentation.viewmodel.ChatScreenViewModel
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun PreviewChatScreenContent() {
//    // Создаем моковый ViewModel
//    val mockViewModel = object : ChatScreenViewModel() {
//        override val state = MutableLiveData(ChatScreenState(
//            messages = listOf(
//                ChatMessage(
//                    id = "1",
//                    message = "Привет!",
//                    date = "2023-10-01T12:34:56Z",
//                    author = ChatUser(
//                        id = "1",
//                        name = "Иван",
//                        surname = "Иванов",
//                        avatar = "https://example.com/avatar.jpg",
//                        role = 1
//                    )
//                )
//            ),
//            currentUserId = "1",
//            isLoading = false,
//            error = null
//        ))
//    }
//
//    // Передаем моковый ViewModel в ChatScreenContent
//    CompositionLocalProvider(
//        LocalViewModelStoreOwner provides mockViewModel
//    ) {
//        ChatScreenContent()
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun PreviewChatMessageItem() {
//    val mockMessage = ChatMessage(
//        id = "1",
//        message = "Привет, как дела?",
//        date = "2023-10-01T12:34:56Z",
//        author = Author(
//            id = "1",
//            name = "Иван",
//            surname = "Иванов",
//            avatar = "https://example.com/avatar.jpg",
//            role = 1
//        )
//    )
//    ChatMessageItem(message = mockMessage, isCurrentUser = false)
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewChatInput() {
//    ChatInput(
//        onSendClicked = { message -> println("Message sent: $message") },
//        onRefreshClicked = { println("Refresh clicked") }
//    )
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun PreviewChatScreen() {
//    ChatScreen()
//}