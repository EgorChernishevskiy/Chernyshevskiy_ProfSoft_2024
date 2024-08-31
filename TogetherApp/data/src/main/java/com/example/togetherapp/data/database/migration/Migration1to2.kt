package com.example.togetherapp.data.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
            CREATE TABLE favorite_courses (
                id TEXT PRIMARY KEY NOT NULL,
                title TEXT NOT NULL,
                description TEXT NOT NULL,
                tags TEXT NOT NULL,
                text TEXT NOT NULL
            )
            """.trimIndent()
        )

        database.execSQL(
            """
            CREATE TABLE favorite_notes (
                id TEXT PRIMARY KEY NOT NULL,
                title TEXT NOT NULL,
                content TEXT NOT NULL,
                author TEXT NOT NULL,
                date TEXT NOT NULL,
                comments TEXT NOT NULL
            )
            """.trimIndent()
        )
    }
}