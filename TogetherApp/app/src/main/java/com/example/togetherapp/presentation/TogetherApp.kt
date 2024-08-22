package com.example.togetherapp.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TogetherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TogetherApp)
            modules(com.example.togetherapp.di.appModules)
        }
    }
}