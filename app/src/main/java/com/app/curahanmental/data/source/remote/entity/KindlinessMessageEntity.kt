package com.app.curahanmental.data.source.remote.entity

data class KindlinessMessageEntity(
//    @get:Exclude
    var id: String? = "",
    val messages: String? = "",
    val time: Long? = 0,
    val poster: String? = ""
)
