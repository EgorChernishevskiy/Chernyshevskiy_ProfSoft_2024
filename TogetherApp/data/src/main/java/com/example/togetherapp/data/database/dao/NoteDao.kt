package com.example.togetherapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.togetherapp.data.database.entity.note.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()
}