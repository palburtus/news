package com.aaks.news.dal

import androidx.room.*
import com.aaks.news.model.Article

@Dao
interface IArticleRepository {

    @Insert
    fun create(article: Article) : Long

    @Query("SELECT * FROM ${ArticleDbConstants.ARTICLE_TABLE_NAME} WHERE ${ArticleDbConstants.URL} = :url LIMIT 1")
    fun get(url: String) : Article?

    @Query("SELECT * FROM ${ArticleDbConstants.ARTICLE_TABLE_NAME}")
    fun get() : List<Article>

    @Update
    fun update(article: Article) : Int

    @Delete
    fun delete(article: Article) : Int
}