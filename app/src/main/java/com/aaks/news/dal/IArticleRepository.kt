package com.aaks.news.dal

import com.aaks.news.model.Article

interface IArticleRepository {

    fun create(article: Article) : Article
    fun get(url: String)
    fun get() : List<Article>
    fun update(article: Article)
    fun delete(url: String)
}