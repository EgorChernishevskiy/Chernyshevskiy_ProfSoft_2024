package com.example.togetherapp.data.database.entity.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.togetherapp.data.database.converter.AuthorConverter
import com.example.togetherapp.data.database.converter.CommentConverter
import com.example.togetherapp.data.database.converter.NoteContentConverter
import com.example.togetherapp.data.database.converter.NoteTopicConverter
import com.example.togetherapp.domain.model.comnote.Author
import com.example.togetherapp.domain.model.comnote.Comment
import com.example.togetherapp.domain.model.comnote.NoteContent
import com.example.togetherapp.domain.utils.NoteTopic

@Entity(tableName = "favorite_notes")
data class FavoriteNoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    @TypeConverters(NoteTopicConverter::class) val topic: NoteTopic,
    @TypeConverters(NoteContentConverter::class) val content: List<NoteContent>,
    @TypeConverters(AuthorConverter::class) val author: Author,
    val date: String,
    @TypeConverters(CommentConverter::class) val comments: List<Comment>
)