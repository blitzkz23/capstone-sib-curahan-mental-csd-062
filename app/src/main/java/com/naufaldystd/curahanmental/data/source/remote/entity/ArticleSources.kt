package com.naufaldystd.curahanmental.data.source.remote.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleSources(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
):Serializable