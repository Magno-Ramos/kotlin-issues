package com.app.githubissues.api

import com.app.githubissues.model.Issue
import io.reactivex.Observable

interface WebService {

    fun getAllIssues(): Observable<List<Issue>>
}