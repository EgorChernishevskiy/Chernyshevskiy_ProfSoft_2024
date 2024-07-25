package com.example.lesson7

import android.app.Application
import com.example.lesson7.di.appModule
import org.koin.core.context.startKoin

class Lesson7App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}