package com.example.togetherapp.presentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatNoteCardDate(dateString: String): String {
    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

    // Удаляем миллисекунды из строки даты
    val cleanedDateString = dateString.substring(0, 19) + "Z"

    val dateTime = Instant.parse(cleanedDateString)
        .atZone(ZoneId.systemDefault()) // Переводим в локальное время
        .toLocalDate()

    return dateTime.format(outputFormatter)
}