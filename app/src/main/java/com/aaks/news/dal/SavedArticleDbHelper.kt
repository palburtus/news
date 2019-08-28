package com.aaks.news.dal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.aaks.news.model.Article

class SavedArticleDbHelper(context: Context) : SQLiteOpenHelper(context, ArticleDbConstants.DATABASE_NAME, null, DATABASE_VERSION), IArticleRepository {

    companion object {
        val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(ArticleDbConstants.ARTICLE_SQL_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun create(article: Article) : Article {
        var db = this.writableDatabase
        article.id = db.insert(ArticleDbConstants.ARTICLE_TABLE_NAME, null, article.buildContentValues())
        db.close()

        return article
    }

    override fun get(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(): List<Article> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(article: Article) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}