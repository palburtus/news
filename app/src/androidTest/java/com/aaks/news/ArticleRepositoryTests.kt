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

        val savedArticleDbHelper = SavedArticleDbHelper(ApplicationProvider.getApplicationContext())

        savedArticleDbHelper.delete(articleToCreate.url)
        savedArticleDbHelper.delete(articleToGet.url)
        savedArticleDbHelper.delete(articleToUpdate.url)
        savedArticleDbHelper.delete(articleToDelete.url)

        articleToGet = savedArticleDbHelper.create(articleToGet)
        articleToUpdate = savedArticleDbHelper.create(articleToUpdate)
        articleToDelete = savedArticleDbHelper.create(articleToDelete)
    }

    @Test
    fun create_insertsArticleAsARowIntoDbReturnsArticle() {

        val savedArticleDbHelper = SavedArticleDbHelper(ApplicationProvider.getApplicationContext())

        var article = buildArticle("create")

        article = savedArticleDbHelper.create(article)

        assertTrue(article.id != null)
        assertTrue(article.id!! >= 0)
    }

    @Test
    fun get_readsOneArticleRowByUrl_returnsArticle() {

        val savedArticleDbHelper = SavedArticleDbHelper(ApplicationProvider.getApplicationContext())
        val article = savedArticleDbHelper.get(articleToGet.url)

        assertAreArticlesEqual(articleToGet, article!!)
    }

    @Test
    fun get_readsAllRowsFromArticleTable_returnsListOfArticles() {

        val savedArticleDbHelper = SavedArticleDbHelper(ApplicationProvider.getApplicationContext())
        val articles = savedArticleDbHelper.get()

        assertEquals(6, articles.size)
    }

    @Test
    fun update_changesTheValuesOfARowInTheDbByUrl_shouldSucceed(){

        val savedArticleDbHelper = SavedArticleDbHelper(ApplicationProvider.getApplicationContext())

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

        val savedArticleDbHelper = SavedArticleDbHelper(ApplicationProvider.getApplicationContext())

        val preDeletedArtcle = savedArticleDbHelper.get(articleToDelete.url)
        assertAreArticlesEqual(articleToDelete, preDeletedArtcle!!)

        val rowsAffected = savedArticleDbHelper.delete(articleToDelete.url)
        assertEquals(1, rowsAffected)

        val deletedArtcle = savedArticleDbHelper.get(articleToDelete.url)
        assertNull(deletedArtcle)
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
