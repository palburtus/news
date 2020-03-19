package com.aaks.news.sqlite

import androidx.test.core.app.ApplicationProvider
import com.aaks.news.BaseArticleRepositoryTests
import com.aaks.news.model.Article

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ArticleDbHelperTests : BaseArticleRepositoryTests(){

    private lateinit var articleToCreate: Article
    private lateinit var articleToGet: Article
    private lateinit var articleToUpdate: Article
    private lateinit var articleToDelete: Article

    @Before
    fun setUp(){

        articleToCreate = buildArticle("create")
        articleToGet = buildArticle("get")
        articleToUpdate = buildArticle("update")
        articleToDelete = buildArticle("delete")

        val savedArticleDbHelper =
            ArticleDbHelper(ApplicationProvider.getApplicationContext())

        savedArticleDbHelper.delete(articleToCreate)
        savedArticleDbHelper.delete(articleToGet)
        savedArticleDbHelper.delete(articleToUpdate)
        savedArticleDbHelper.delete(articleToDelete)

        articleToGet.id = savedArticleDbHelper.create(articleToGet)
        articleToUpdate.id = savedArticleDbHelper.create(articleToUpdate)
        articleToDelete.id = savedArticleDbHelper.create(articleToDelete)
    }

    @Test
    fun create_insertsArticleAsARowIntoDbReturnsArticle() {

        val savedArticleDbHelper =
            ArticleDbHelper(ApplicationProvider.getApplicationContext())

        var article = buildArticle("create")

        article.id = savedArticleDbHelper.create(article)

        assertTrue(article.id != null)
        assertTrue(article.id!! >= 0)
    }

    @Test
    fun get_readsOneArticleRowByUrl_returnsArticle() {

        val savedArticleDbHelper =
            ArticleDbHelper(ApplicationProvider.getApplicationContext())
        val article = savedArticleDbHelper.get(articleToGet.url)

        assertAreArticlesEqual(articleToGet, article!!)
    }

    @Test
    fun get_readsAllRowsFromArticleTable_returnsListOfArticles() {

        val savedArticleDbHelper =
            ArticleDbHelper(ApplicationProvider.getApplicationContext())
        val articles = savedArticleDbHelper.get()

        assertEquals(6, articles.size)
    }

    @Test
    fun update_changesTheValuesOfARowInTheDbByUrl_shouldSucceed(){

        val savedArticleDbHelper =
            ArticleDbHelper(ApplicationProvider.getApplicationContext())

        val preUpdatedArticle = savedArticleDbHelper.get(articleToUpdate.url)
        assertAreArticlesEqual(articleToUpdate, preUpdatedArticle!!)

        articleToUpdate.title = "post update title"
        articleToUpdate.description = "post update description"

        val rowsAffected = savedArticleDbHelper.update(articleToUpdate)
        assertEquals(1, rowsAffected)

        val postUpdateArticle = savedArticleDbHelper.get(articleToUpdate.url)
        assertAreArticlesEqual(articleToUpdate, postUpdateArticle!!)
    }

    @Test
    fun delete_removesArticleRowFromDbByUrl_shouldSucceed(){

        val savedArticleDbHelper =
            ArticleDbHelper(ApplicationProvider.getApplicationContext())

        val preDeletedArtcle = savedArticleDbHelper.get(articleToDelete.url)
        assertAreArticlesEqual(articleToDelete, preDeletedArtcle!!)

        val rowsAffected = savedArticleDbHelper.delete(articleToDelete)
        assertEquals(1, rowsAffected)

        val deletedArtcle = savedArticleDbHelper.get(articleToDelete.url)
        assertNull(deletedArtcle)
    }
}
