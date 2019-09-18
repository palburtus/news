package com.aaks.news.dal.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aaks.news.dal.ArticleDbConstants
import com.aaks.news.dal.IArticleRepository
import com.aaks.news.model.Article

@Database(entities = arrayOf(Article::class), version = ArticleDbConstants.DATABASE_VERSION)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleRepository(): IArticleRepository
}
