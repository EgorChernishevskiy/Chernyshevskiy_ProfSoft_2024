package com.example.togetherapp.presentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateProfile(date: String): String {
    val inputFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.getDefault())

    return try {
        val localDateTime =
            LocalDateTime.parse(date, inputFormatter).atZone(ZoneOffset.UTC).toLocalDate()
        localDateTime.format(outputFormatter)
    } catch (e: Exception) {
        date
    }
}