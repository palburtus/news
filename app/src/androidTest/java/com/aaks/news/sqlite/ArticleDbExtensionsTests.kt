package com.aaks.news.sqlite

import android.provider.BaseColumns
import com.aaks.news.dal.ArticleDbConstants
import com.aaks.news.dal.buildContentValues
import com.aaks.news.model.Article
import org.junit.Assert.*
import org.junit.Test

class ArticleDbExtensionsTests {

    @Test
    fun buildContentValues_buildsSqlLiteContentValuesFromArticle_returnsContentValues(){
        val article = Article(3, "http://www.test.com", "Test Title", "Test description")
        val contentValues = article.buildContentValues()

        assertEquals(4, contentValues.size())
        assertEquals("3", contentValues[BaseColumns._ID].toString())
        assertEquals("http://www.test.com", contentValues[ArticleDbConstants.URL].toString())
        assertEquals("Test Title", contentValues[ArticleDbConstants.TITLE].toString())
        assertEquals("Test description", contentValues[ArticleDbConstants.DESCRIPTION].toString())

    }

    @Test
    fun buildContentValues_articleIdIsLessThanZero_returnsContentValues(){
        val article = Article(-1, "http://www.test.com", "Test Title", "Test description")
        val contentValues = article.buildContentValues()
        assertEquals(3, contentValues.size())
        assertEquals("http://www.test.com", contentValues[ArticleDbConstants.URL].toString())
        assertEquals("Test Title", contentValues[ArticleDbConstants.TITLE].toString())
        assertEquals("Test description", contentValues[ArticleDbConstants.DESCRIPTION].toString())
    }

}