package com.app.githubissues

import android.app.Application
import com.app.githubissues.di.AppComponent
import com.app.githubissues.di.DaggerAppComponent

class AppApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .factory()
            .create(this)
    }
}