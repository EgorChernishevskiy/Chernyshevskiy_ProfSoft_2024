package com.example.togetherapp.data.mappers.locnote

import com.example.togetherapp.data.database.entity.note.NoteEntity
import com.example.togetherapp.domain.model.locnote.LocNote

interface LocNoteMapper {
    fun mapToDomain(noteEntity: NoteEntity): LocNote
    fun mapToEntity(note: LocNote): NoteEntity
}