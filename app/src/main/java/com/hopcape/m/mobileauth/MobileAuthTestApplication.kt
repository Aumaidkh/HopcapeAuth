package com.hopcape.m.mobileauth

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobileAuthTestApplication: Application(){
    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
    }
}