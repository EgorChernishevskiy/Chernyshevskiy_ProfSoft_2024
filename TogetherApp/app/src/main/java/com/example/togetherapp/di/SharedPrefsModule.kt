package com.example.togetherapp.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.dsl.module

private const val APP_PREFS = "app_prefs"

val sharedPrefsModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
    }
}