package com.aaks.news

import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.aaks.news.dal.SavedArticleDbHelper
import com.aaks.news.model.Article

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ArticleRepositoryTests {

    private lateinit var articleToGet: Article
    private lateinit var articleToUpdate: Article
    private lateinit var articleToDelete: Article

    @Before
    fun setUp(){
        articleToGet = buildArticle("get")
        articleToUpdate = buildArticle("update")
        articleToDelete = buildArticle("delete")
    }

    @Test
    fun create_insertsArticleAsARowIntoDbReturnsArticle() {
        
        val savedArticleDbHelper = SavedArticleDbHelper(ApplicationProvider.getApplicationContext())

        var article = buildArticle("create")

        article = savedArticleDbHelper.create(article)

        assertTrue(article.id != null)
        assertTrue(article.id!! >= 0)
    }

    fun assertAreArticlesEqual(expected: Article, actual: Article){
        assertEquals(expected.id, actual.id)
        assertEquals(expected.url, actual.url)
        assertEquals(expected.title, actual.title)
        assertEquals(expected.description, actual.description)
    }

    fun buildArticle(tag: String) : Article{
        return Article(null, "https://article${tag}.com", "$tag title", "$tag description")
    }
}
