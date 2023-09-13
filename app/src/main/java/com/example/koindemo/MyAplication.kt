package com.example.koindemo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyAplication)
            modules(listOf(
                KoinModule.repositoryModule,
                KoinModule.viewModelModule,
                KoinModule.retrofitModule,
                KoinModule.apiModule
            ))
        }
    }
}