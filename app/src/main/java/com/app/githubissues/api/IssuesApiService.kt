package com.app.githubissues.api

import com.app.githubissues.model.Issue
import io.reactivex.Observable
import javax.inject.Inject

class IssuesApiService
@Inject constructor(private val retrofitService: RetrofitService) : WebService {

    override fun getAllIssues(): Observable<List<Issue>> {
        return retrofitService.fetchIssues()
    }
}