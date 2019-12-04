package com.app.githubissues.di

import android.app.Application
import com.app.githubissues.detail.IssueDetailActivity
import com.app.githubissues.di.module.ApplicationModule
import com.app.githubissues.di.module.NetworkModule
import com.app.githubissues.issues.IssuesActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(issuesActivity: IssuesActivity)

    fun inject(issuesDetailActivity: IssueDetailActivity)
}