package com.example.togetherapp.presentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatNoteCardDate(dateString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
    val date = LocalDate.parse(dateString, inputFormatter)
    return date.format(outputFormatter)
}
