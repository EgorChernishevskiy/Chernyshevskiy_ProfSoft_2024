package com.example.togetherapp.presentation.ui.favoritescreen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.presentation.event.FavoriteScreenEvent
import com.example.togetherapp.presentation.state.FavoriteScreenState
import com.example.togetherapp.presentation.ui.mainscreen.components.CustomHorizontalPager
import com.example.togetherapp.presentation.ui.mainscreen.components.SectionTitle
import com.example.togetherapp.presentation.viewmodel.FavoriteScreenViewModel

@Composable
fun FavoriteCourses(title: String, courses: List<Course>, onShowAllClick: () -> Unit, navController: NavHostController){
    Spacer(modifier = Modifier.height(20.dp))

    SectionTitle(title, showAll = true) {
        onShowAllClick()
    }
    Spacer(modifier = Modifier.height(12.dp))

    CustomHorizontalPager(courses = courses, navController)
}