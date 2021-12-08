package com.app.curahanmental.data.source.remote

import com.app.curahanmental.utils.StatusResponse

class ApiResponses<T>(val status: StatusResponse, val body: T, val msg: String?) {
    companion object{
        fun <T> success(body: T): ApiResponses<T> = ApiResponses(StatusResponse.SUCCESS, body, null)
        fun <T> empty(body: T): ApiResponses<T> = ApiResponses(StatusResponse.EMPTY, body, null)
        fun <T> error(body: T): ApiResponses<T> = ApiResponses(StatusResponse.ERROR, body, null)
    }
}