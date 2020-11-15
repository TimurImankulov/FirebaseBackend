package com.example.firebasebackend

import android.app.Application
import com.example.firebasebackend.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FirebaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FirebaseApp)
            modules(appModules)
        }
    }
}