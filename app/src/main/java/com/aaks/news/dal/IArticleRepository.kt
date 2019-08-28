package com.aaks.news.dal

import com.aaks.news.model.Article

interface IArticleRepository {

    fun create(article: Article) : Article
    fun get(url: String) : Article?
    fun get() : List<Article>
    fun update(article: Article) : Int
    fun delete(url: String) : Int
}