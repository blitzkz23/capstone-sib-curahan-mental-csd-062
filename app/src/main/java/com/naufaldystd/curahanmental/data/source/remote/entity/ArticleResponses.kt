package com.naufaldystd.curahanmental.data.source.remote.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleResponses(
    @SerializedName("articles")
    val listOfArticles: List<ArticleEntity>? = null
):Serializable
