package com.diwixis.filmlibrary.api

@Suppress(names = ["unused"])
sealed class Response<out T> {
    companion object {
        fun <T> load() = Load<T>()
        fun <T> success(value: T) = Success(value)
        fun <T> failure(error: Throwable) = Failure<T>(error)
    }
}

class Load<out T> : Response<T>()
data class Success<out T>(val value: T) : Response<T>()
data class Failure<out T>(val error: Throwable) : Response<T>()