package com.app.curahanmental.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.curahanmental.data.source.remote.entity.ArticleSources
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "articles")
data class Articles(
    @PrimaryKey
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,
    @ColumnInfo(name = "content")
    val content: String,
)
