package com.app.githubissues.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.app.githubissues.AppApplication
import com.app.githubissues.R
import com.app.githubissues.model.Issue
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_issue_detail.*
import kotlinx.android.synthetic.main.adapter_item_issue.tv_title
import java.text.DateFormat
import javax.inject.Inject

class IssueDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var dateFormat: DateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as AppApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.extras?.run {
            val issue: Issue = getSerializable(ISSUE_INTENT_KEY) as Issue
            bindView(issue)
        }
    }

    private fun bindView(issue: Issue) {
        tv_title?.text = issue.title
        tv_description?.text = issue.body
        tv_date_time?.text = dateFormat.format(issue.created)

        Picasso.get()
            .load(issue.user.avatar)
            .into(profile_image)

        btn_access.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(issue.url)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ISSUE_INTENT_KEY = "com.app.ISSUE_INTENT_KEY"
    }
}
