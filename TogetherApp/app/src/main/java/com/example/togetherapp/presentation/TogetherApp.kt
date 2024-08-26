package com.example.togetherapp.presentation

import android.app.Application
import androidx.room.Room
import com.example.togetherapp.data.database.NoteDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TogetherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TogetherApp)
            modules(com.example.togetherapp.di.appModules)
            printLogger(Level.DEBUG)
        }
    }
}