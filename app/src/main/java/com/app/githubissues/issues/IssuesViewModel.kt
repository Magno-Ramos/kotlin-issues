package com.app.githubissues.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.githubissues.model.Issue
import com.app.githubissues.model.Resource
import javax.inject.Inject

class IssuesViewModel
@Inject constructor(private val issuesRepository: IssuesRepository) : ViewModel() {

    fun getIssues(): LiveData<Resource<List<Issue>>> {
        return issuesRepository.getResourceIssues()
    }


    override fun onCleared() {
        super.onCleared()
        issuesRepository.clear()
    }
}