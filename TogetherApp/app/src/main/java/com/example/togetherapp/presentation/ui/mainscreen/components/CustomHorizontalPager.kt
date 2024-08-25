package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomHorizontalPager(courses: List<Course>, navController: NavHostController) {
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
                    tags = course.tags,
                    onClick = { navController.navigate("details/${course.id}/${page}") }
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