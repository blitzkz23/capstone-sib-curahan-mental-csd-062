package com.app.curahanmental.data.source.remote.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ArticleEntity(
    @SerializedName("sources")
    val source: ArticleSources? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("content")
    val content: String? = null
):Serializable
