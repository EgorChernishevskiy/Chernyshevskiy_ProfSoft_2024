package com.example.togetherapp.presentation.ui.splashscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.togetherapp.R
import com.example.togetherapp.presentation.state.SplashScreenState
import com.example.togetherapp.presentation.ui.components.Logo
import com.example.togetherapp.presentation.ui.theme.TogetherAppTheme
import com.example.togetherapp.presentation.viewmodel.SplashScreenViewModel

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SplashScreenViewModel
) {
    val state by viewModel.state.observeAsState(SplashScreenState())

    LaunchedEffect(state) {
        when {
            state.navigateToHome -> navController.navigate("home") { //поменять на "home"
                popUpTo("splash") { inclusive = true }
            }
            state.navigateToLogin -> navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFD80C))
    ) {
        // Лого
        Logo(
            modifier = Modifier
                .align(Alignment.Center)
                .width(65.dp)
        )

        // Текст "ВМЕСТЕ"
        Image(
            painter = painterResource(id = R.drawable.together_label),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 232.dp)
                .width(69.dp)
                .height(12.dp)
        )
    }
}

//@Preview
//@Composable
//private fun SplashScreenContentPreview() {
//    TogetherAppTheme {
//        SplashScreenContent()
//    }
//}