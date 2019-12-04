package com.app.githubissues.di.module

import dagger.Module
import dagger.Provides
import java.text.DateFormat
import java.util.*

@Module
class ApplicationModule {

    @Provides
    fun providesLocale() : Locale{
        return Locale("pt", "BR")
    }

    @Provides
    fun providesDateFormat(locale: Locale): DateFormat {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, locale)
    }
}