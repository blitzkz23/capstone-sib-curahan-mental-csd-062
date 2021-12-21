package com.app.curahanmental.data.source.remote


sealed class ApiResponses<out R> {
    data class Success<out T>(val data: T) : ApiResponses<T>()
    data class Error(val errorMessage: String) : ApiResponses<Nothing>()
    object Empty : ApiResponses<Nothing>()
}