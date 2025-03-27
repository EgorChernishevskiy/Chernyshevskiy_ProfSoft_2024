package com.example.togetherapp.data.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE favorite_notes ADD COLUMN topic TEXT NOT NULL DEFAULT 'OIL'")
    }
}
