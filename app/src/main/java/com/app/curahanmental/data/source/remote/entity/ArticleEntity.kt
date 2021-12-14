package com.app.curahanmental.data.source.remote.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
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
