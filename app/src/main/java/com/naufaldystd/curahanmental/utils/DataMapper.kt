package com.naufaldystd.curahanmental.utils

import com.naufaldystd.curahanmental.data.source.local.entity.Articles
import com.naufaldystd.curahanmental.data.source.local.entity.ArticlesModel
import com.naufaldystd.curahanmental.data.source.remote.entity.ArticleEntity

object DataMapper {
    fun mapResponsesToEntities(input: List<ArticleEntity>): List<Articles> {
        val articleList = ArrayList<Articles>()
        input.map {
            val article = Articles(
                title = it.title,
                description = it.description,
                url = it.url,
                urlToImage = it.urlToImage,
                author = it.author,
                publishedAt = it.publishedAt,
                content = it.content
            )
            articleList.add(article)
        }
        return articleList
    }

    fun mapEntitiesToDomain(input: List<Articles>): List<ArticlesModel> =
        input.map {
            ArticlesModel(
                description = it.description,
                title = it.title,
                author = it.author,
                url = it.url,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }
}