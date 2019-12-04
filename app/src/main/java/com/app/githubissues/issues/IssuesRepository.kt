package com.app.githubissues.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.githubissues.api.WebService
import com.app.githubissues.model.Issue
import com.app.githubissues.model.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class IssuesRepository @Inject constructor(private val webService: WebService) {

    private val compositeDisposable = CompositeDisposable()

    fun getResourceIssues(): LiveData<Resource<List<Issue>>> {
        val mutableData = MutableLiveData<Resource<List<Issue>>>()

        mutableData.value = Resource(Resource.Status.LOADING, null, "Loading")
        val disposable = webService.getAllIssues()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mutableData.postValue(Resource(Resource.Status.SUCCESS, it, "Success")) },
                { mutableData.postValue(Resource(Resource.Status.FAILED, null, "Request failed")) }
            )
        compositeDisposable.add(disposable)

        return mutableData
    }

    fun clear() {
        compositeDisposable.clear()
    }
}