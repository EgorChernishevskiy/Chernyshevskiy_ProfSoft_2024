package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.locnote.LocNote

interface LocNoteRepository {
    suspend fun getAllLocalNotes(): List<LocNote>
    suspend fun getLocalNoteById(id: Int): LocNote?
    suspend fun createLocalNote(note: LocNote)
}