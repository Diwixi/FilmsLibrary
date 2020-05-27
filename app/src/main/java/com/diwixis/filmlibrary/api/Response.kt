package com.diwixis.filmlibrary.api

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
@Suppress(names = ["unused"])
sealed class Response<out T> {
    companion object {
        fun <T> success(value: T) = Success(value)
        fun <T> failure(error: Throwable) = Failure<T>(error)
    }
}

data class Success<out T>(val value: T) : Response<T>()
data class Failure<out T>(val error: Throwable) : Response<T>()