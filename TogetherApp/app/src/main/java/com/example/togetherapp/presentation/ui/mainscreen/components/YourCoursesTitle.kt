package com.example.togetherapp.presentation.ui.mainscreen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun YourCoursesTitle(){
    Spacer(modifier = Modifier.height(20.dp))

    SectionTitle(title = "Ваши курсы", showAll = true)
}