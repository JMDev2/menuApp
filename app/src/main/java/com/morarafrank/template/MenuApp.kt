package com.morarafrank.template

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MenuApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}