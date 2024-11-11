package com.example.ratemovies

import android.app.Application
import com.example.ratemovies.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RateMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RateMoviesApp)
            androidLogger()
            modules(appModule)
        }
    }
}