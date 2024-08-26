package com.example.togetherapp.data.mappers.locnote

import com.example.togetherapp.data.database.entity.NoteContentEntity
import com.example.togetherapp.data.database.entity.NoteEntity
import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.model.locnote.LocNoteContent

class LocNoteMapperImpl : LocNoteMapper {
    override fun mapToDomain(noteEntity: NoteEntity): LocNote {
        return LocNote(
            id = noteEntity.id,
            title = noteEntity.title,
            content = noteEntity.content.map {
                LocNoteContent(it.text, it.image)
            },
            date = noteEntity.date
        )
    }

    override fun mapToEntity(note: LocNote): NoteEntity {
        return NoteEntity(
            id = note.id,
            title = note.title,
            content = note.content.map {
                NoteContentEntity(it.text, it.image)
            },
            date = note.date
        )
    }
}