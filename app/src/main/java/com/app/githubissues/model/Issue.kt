package com.app.githubissues.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Issue(
    val id: String,
    val title: String,
    val state: String,
    val body: String,
    val user: User,

    @SerializedName("html_url")
    val url: String,

    @SerializedName("created_at")
    val created: Date
) : Serializable