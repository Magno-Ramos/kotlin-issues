package com.app.githubissues.api

import com.app.githubissues.model.Issue
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitService {

    @GET("issues")
    fun fetchIssues(): Observable<List<Issue>>
}