package com.app.githubissues.model

data class Resource<T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    enum class Status {
        LOADING,
        SUCCESS,
        FAILED
    }
}