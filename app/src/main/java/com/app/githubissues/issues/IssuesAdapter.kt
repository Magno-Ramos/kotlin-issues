package com.app.githubissues.issues

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.githubissues.R
import com.app.githubissues.model.Issue
import kotlinx.android.synthetic.main.adapter_item_issue.view.*
import javax.inject.Inject

class IssuesAdapter @Inject constructor() : RecyclerView.Adapter<IssuesAdapter.ViewHolder>() {

    var listener: ((Issue) -> Unit)? = null
    var list: List<Issue>? = null

    fun submitList(issues: List<Issue>) {
        list = issues
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = View.inflate(parent.context, R.layout.adapter_item_issue, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issue = list!![position]
        holder.bindView(issue)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { listener?.invoke(list!![adapterPosition]) }
        }

        fun bindView(issue: Issue) {
            itemView.tv_title?.text = issue.title
            itemView.tv_status?.text = issue.state
        }
    }
}