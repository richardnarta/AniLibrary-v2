package com.v2.anilibrary

import android.app.Application
import com.v2.anilibrary.di.initKoin
import org.koin.android.ext.koin.androidContext

class AniLibrary: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AniLibrary)
        }
    }
}