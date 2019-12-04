package com.app.githubissues.issues

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.githubissues.AppApplication
import com.app.githubissues.R
import com.app.githubissues.detail.IssueDetailActivity
import com.app.githubissues.model.Issue
import com.app.githubissues.model.Resource
import kotlinx.android.synthetic.main.activity_issues.*
import javax.inject.Inject

class IssuesActivity : AppCompatActivity() {

    @Inject
    lateinit var issuesViewModel: IssuesViewModel

    @Inject
    lateinit var issuesAdapter: IssuesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues)

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = issuesAdapter

        issuesAdapter.listener = { onClickIssue(it) }

        issuesViewModel
            .getIssues()
            .observe(this, Observer {
                when (it.status) {
                    Resource.Status.LOADING -> showProgress()
                    Resource.Status.SUCCESS -> {
                        hideProgress()
                        issuesAdapter.submitList(it.data!!)
                    }
                    Resource.Status.FAILED -> {
                        hideProgress()
                        displayError(it.message!!)
                    }
                }
            })
    }

    private fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    private fun displayError(message: String) {
        Toast.makeText(this@IssuesActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun onClickIssue(issue: Issue) {
        val intent = Intent(this, IssueDetailActivity::class.java)
        intent.putExtra(IssueDetailActivity.ISSUE_INTENT_KEY, issue)
        startActivity(intent)
    }
}


