package com.aaks.news.dal

import android.provider.BaseColumns

class ArticleDbConstants {

    companion object {

        const val DATABASE_VERSION = 1

        const val DATABASE_NAME = "article.db"
        const val ARTICLE_TABLE_NAME = "article"

        const val URL = "url"
        const val TITLE = "title"
        const val DESCRIPTION = "description"

        const val ARTICLE_SQL_CREATE = "CREATE TABLE $ARTICLE_TABLE_NAME (${BaseColumns._ID}  INTEGER PRIMARY KEY AUTOINCREMENT, $URL TEXT, $TITLE TEXT, $DESCRIPTION TEXT)"
    }
}