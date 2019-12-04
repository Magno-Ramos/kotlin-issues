package com.app.githubissues.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    val id: String,
    val login: String,

    @SerializedName("avatar_url")
    val avatar: String
) : Serializable