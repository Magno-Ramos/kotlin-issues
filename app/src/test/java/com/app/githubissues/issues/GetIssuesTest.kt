package com.app.githubissues.issues

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.githubissues.api.WebService
import com.app.githubissues.model.Resource
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.mockito.Mockito.`when` as whenMock


class GetIssuesTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun testRequestStatus() {
        val sm = mock(WebService::class.java)
        whenMock(sm.getAllIssues()).thenReturn(Observable.just(arrayListOf()))

        val repository = IssuesRepository(sm)
        val liveData = repository.getResourceIssues()

        assertThat(liveData.value!!.status, equalTo(Resource.Status.LOADING))

        val latch = CountDownLatch(1)
        latch.await(2, TimeUnit.SECONDS)

        assertThat(liveData.value!!.status, equalTo(Resource.Status.SUCCESS))
    }

    @Test
    fun testEmptyList() {
        val sm = mock(WebService::class.java)
        whenMock(sm.getAllIssues()).thenReturn(Observable.just(arrayListOf()))

        sm.getAllIssues().subscribe {
            assertThat(it.size, equalTo(0))
        }

        val repository = IssuesRepository(sm)
        val liveData = repository.getResourceIssues()

        val latch = CountDownLatch(1)
        latch.await(2, TimeUnit.SECONDS)

        assertThat(liveData.value!!.data!!.size, equalTo(0))
    }
}