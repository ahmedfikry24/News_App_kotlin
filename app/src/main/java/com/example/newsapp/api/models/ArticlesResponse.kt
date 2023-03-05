package com.example.newsapp.api.models

import java.io.Serializable

class ArticlesResponse(
    val totalResults: Int? = null,
    val articles: List<Article?>? = null,
    val status: String? = null
)

class Article(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: Tab? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null
)
