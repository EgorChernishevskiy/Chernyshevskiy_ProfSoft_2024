package com.example.togetherapp.data.repository

import com.example.togetherapp.data.database.dao.NoteDao
import com.example.togetherapp.data.mappers.locnote.LocNoteMapper
import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.repository.LocNoteRepository

class LocNoteRepositoryImpl(
    private val noteDao: NoteDao,
    private val noteMapper: LocNoteMapper
) : LocNoteRepository {

    override suspend fun getAllLocalNotes(): List<LocNote> {
        return noteDao.getAllNotes().map { noteMapper.mapToDomain(it) }
    }

    override suspend fun getLocalNoteById(id: String): LocNote? {
        return noteDao.getNoteById(id)?.let { noteMapper.mapToDomain(it) }
    }

    override suspend fun createLocalNote(note: LocNote) {
        noteDao.insertNote(noteMapper.mapToEntity(note))
    }
}