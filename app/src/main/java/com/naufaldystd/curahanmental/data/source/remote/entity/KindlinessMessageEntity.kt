package com.naufaldystd.curahanmental.data.source.remote.entity

import com.google.firebase.database.Exclude

data class KindlinessMessageEntity(
    @get:Exclude
    var id: String? = "",
    val messages: String? = "",
    val time: Long? = 0,
    val poster: String? = ""
)
