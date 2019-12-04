package com.app.githubissues.di.module

import com.app.githubissues.api.IssuesApiService
import com.app.githubissues.api.RetrofitService
import com.app.githubissues.api.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesRetrofitService(): RetrofitService {
        val gson = GsonBuilder().setLenient().create()

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val http = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(http)
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(RetrofitService::class.java)
    }

    @Provides
    fun providesWebService(retrofitService: RetrofitService): WebService {
        return IssuesApiService(retrofitService)
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/repos/JetBrains/kotlin/"
    }
}