package com.example.togetherapp.data.database.entity.note

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.togetherapp.data.database.converter.NoteContentConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    @TypeConverters(NoteContentConverter::class)
    val content: List<NoteContentEntity>,
    val date: String = getCurrentFormattedDate()
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentFormattedDate(): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            return LocalDateTime.now().format(formatter)
        }
    }
}