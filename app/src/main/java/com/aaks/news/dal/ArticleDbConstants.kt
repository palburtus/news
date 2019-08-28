package com.aaks.news.dal

import android.provider.BaseColumns

class ArticleDbConstants {

    companion object {

        val DATABASE_NAME = "article.db"
        val ARTICLE_TABLE_NAME = "article"

        val URL = "url"
        val TITLE = "title"
        val DESCRIPTION = "description"

        val ARTICLE_SQL_CREATE = "CREATE TABLE $ARTICLE_TABLE_NAME (${BaseColumns._ID}  INTEGER PRIMARY KEY AUTOINCREMENT, $URL TEXT, $TITLE TEXT, $DESCRIPTION TEXT)"
    }
}