package com.example.togetherapp.data.mappers.locnote

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.togetherapp.data.database.entity.note.NoteContentEntity
import com.example.togetherapp.data.database.entity.note.NoteEntity
import com.example.togetherapp.domain.model.comnote.NoteContent
import com.example.togetherapp.domain.model.locnote.LocNote

class LocNoteMapperImpl : LocNoteMapper {
    override fun mapToDomain(noteEntity: NoteEntity): LocNote {
        return LocNote(
            id = noteEntity.id,
            title = noteEntity.title,
            content = noteEntity.content.map {
                NoteContent(it.text, it.image)
            },
            date = noteEntity.date
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun mapToEntity(note: LocNote): NoteEntity {
        return NoteEntity(
            id = 0,
            title = note.title,
            content = note.content.map {
                NoteContentEntity(it.text, it.image)
            }
        )
    }
}