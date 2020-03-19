package com.aaks.news.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aaks.news.dal.ArticleDbConstants
import com.aaks.news.BaseArticleRepositoryTests
import com.aaks.news.model.Article
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ArticleDatabaseTests : BaseArticleRepositoryTests() {

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

        val db = Room.databaseBuilder(ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java, ArticleDbConstants.DATABASE_NAME).fallbackToDestructiveMigration().build()

        val existingArticles = db.articleRepository().get();
        for(a in existingArticles){
            db.articleRepository().delete(a)
        }

        db.articleRepository().delete(articleToCreate)
        db.articleRepository().delete(articleToGet)
        db.articleRepository().delete(articleToUpdate)
        db.articleRepository().delete(articleToDelete)

        articleToGet.id = db.articleRepository().create(articleToGet)
        articleToUpdate.id = db.articleRepository().create(articleToUpdate)
        articleToDelete.id = db.articleRepository().create(articleToDelete)
    }

    @Test
    fun create_insertsArticleAsARowIntoDbReturnsArticle() {

        val db = Room.databaseBuilder(ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java, ArticleDbConstants.DATABASE_NAME).build()

        val article = buildArticle("create")

        article.id = db.articleRepository().create(article)

        assertTrue(article.id >= 0)
    }

    @Test
    fun get_readsOneArticleRowByUrl_returnsArticle() {

        val db = Room.databaseBuilder(ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java, ArticleDbConstants.DATABASE_NAME).build()

        val article = db.articleRepository().get(articleToGet.url)

        assertAreArticlesEqual(articleToGet, article!!)
    }

    @Test
    fun get_readsAllRowsFromArticleTable_returnsListOfArticles() {

        val db = Room.databaseBuilder(ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java, ArticleDbConstants.DATABASE_NAME).build()

        val articles = db.articleRepository().get()

        assertEquals(3, articles.size)
    }

    @Test
    fun update_changesTheValuesOfARowInTheDbByUrl_shouldSucceed(){

        val db = Room.databaseBuilder(ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java, ArticleDbConstants.DATABASE_NAME).build()

        val preUpdatedArticle = db.articleRepository().get(articleToUpdate.url)
        assertAreArticlesEqual(articleToUpdate, preUpdatedArticle!!)

        articleToUpdate.title = "post update title"
        articleToUpdate.description = "post update description"

        val rowsAffected = db.articleRepository().update(articleToUpdate)
        assertEquals(1, rowsAffected)

        val postUpdateArticle = db.articleRepository().get(articleToUpdate.url)
        assertAreArticlesEqual(articleToUpdate, postUpdateArticle!!)
    }

    @Test
    fun delete_removesArticleRowFromDbByUrl_shouldSucceed(){

        val db = Room.databaseBuilder(ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java, ArticleDbConstants.DATABASE_NAME).build()

        val preDeletedArtcle = db.articleRepository().get(articleToDelete.url)
        assertAreArticlesEqual(articleToDelete, preDeletedArtcle!!)

        val rowsAffected = db.articleRepository().delete(articleToDelete)
        assertEquals(1, rowsAffected)

        val deletedArtcle = db.articleRepository().get(articleToDelete.url)
        assertNull(deletedArtcle)
    }
}