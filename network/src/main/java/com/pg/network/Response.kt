package com.pg.network

@Suppress(names = ["unused"])
sealed class Response<out T> {
    companion object {
        fun <T> success(value: T) = Success(value)
        fun <T> failure(error: Throwable) = Failure<T>(error)
    }
}

data class Success<out T>(val value: T) : Response<T>()
data class Failure<out T>(val error: Throwable) : Response<T>()

suspend fun <T> response(block: suspend () -> T): Response<T> {
    return try {
        Success(value = block())
    } catch (e: Throwable) {
        Failure(error = e)
    }
}