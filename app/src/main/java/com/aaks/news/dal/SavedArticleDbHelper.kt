package com.aaks.news.dal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
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
        val db = this.writableDatabase
        article.id = db.insert(ArticleDbConstants.ARTICLE_TABLE_NAME, null, article.buildContentValues())
        db.close()

        return article
    }

    override fun get(url: String) : Article? {

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${ArticleDbConstants.ARTICLE_TABLE_NAME} WHERE URL = \"$url\"", null)

        if(cursor != null && cursor.moveToFirst()){

            db.close()

            return Article(
                cursor.getLong(cursor.getColumnIndex(BaseColumns._ID)),
                cursor.getString(cursor.getColumnIndex(ArticleDbConstants.URL)),
                cursor.getString(cursor.getColumnIndex(ArticleDbConstants.TITLE)),
                cursor.getString(cursor.getColumnIndex(ArticleDbConstants.DESCRIPTION)))
        }

        db.close()

        return null
    }

    override fun get(): List<Article> {

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${ArticleDbConstants.ARTICLE_TABLE_NAME}", null)

        var articles = mutableListOf<Article>()

        while(cursor != null && cursor.moveToNext()){

            val article = Article(
                cursor.getLong(cursor.getColumnIndex(BaseColumns._ID)),
                cursor.getString(cursor.getColumnIndex(ArticleDbConstants.URL)),
                cursor.getString(cursor.getColumnIndex(ArticleDbConstants.TITLE)),
                cursor.getString(cursor.getColumnIndex(ArticleDbConstants.DESCRIPTION)))

            articles.add(article)

        }

        db.close()
        return articles
    }

    override fun update(article: Article): Int {

        val db = this.writableDatabase
        val whereClause = "${ArticleDbConstants.URL} = \"${article.url}\""

        val rowsAffected = db.update(ArticleDbConstants.ARTICLE_TABLE_NAME, article.buildContentValues(), whereClause, null)
        db.close()

        return rowsAffected
    }

    override fun delete(url: String): Int {

        val db = this.writableDatabase
        val whereClause = "${ArticleDbConstants.URL} = \"$url\""

        val rowsAffected = db.delete(ArticleDbConstants.ARTICLE_TABLE_NAME, whereClause, null)
        db.close()

        return rowsAffected
    }


}