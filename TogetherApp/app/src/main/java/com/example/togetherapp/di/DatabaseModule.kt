package com.example.togetherapp.di

import android.content.Context
import androidx.room.Room
import com.example.togetherapp.data.database.NoteDatabase
import com.example.togetherapp.data.database.migration.MIGRATION_1_2
import com.example.togetherapp.data.database.migration.MIGRATION_2_3
import org.koin.dsl.module

private const val NOTE_DATABASE = "note_database"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get<Context>(),
            NoteDatabase::class.java,
            NOTE_DATABASE
        )
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .allowMainThreadQueries()
            .build()
    }
    single { get<NoteDatabase>().noteDao() }
    single { get<NoteDatabase>().favoriteDao() }
}