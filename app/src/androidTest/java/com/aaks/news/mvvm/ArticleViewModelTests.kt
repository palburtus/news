package com.aaks.news.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aaks.news.model.Article
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ArticleViewModelTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun isTitleVisible_articleIsNull_returnsFalse(){

        val articleViewModel = ArticleViewModel()
        articleViewModel.setArticle(null)

        assertFalse(articleViewModel.isTitleVisible())
    }

    @Test
    fun isTitleVisible_titleIsEmpty_returnsFalse(){

        val article = Article(0, "", "", "")

        val articleViewModel = ArticleViewModel()
        articleViewModel.setArticle(article)

        assertFalse(articleViewModel.isTitleVisible())
    }

    @Test
    fun isTitleVisible_titleIsNotEmpty_returnTrue(){

        val article = Article(0, "", "something", "")

        val articleViewModel = ArticleViewModel()
        articleViewModel.setArticle(article)

        assertTrue(articleViewModel.isTitleVisible())
    }

    @Test
    fun isDescriptionVisible_articleIsNull_returnsFalse(){

        val articleViewModel = ArticleViewModel()
        articleViewModel.setArticle(null)

        assertFalse(articleViewModel.isDescriptionVisible())
    }

    @Test
    fun isDescriptionVisible_titleIsEmpty_returnsFalse(){

        val article = Article(0, "", "", "")

        val articleViewModel = ArticleViewModel()
        articleViewModel.setArticle(article)

        assertFalse(articleViewModel.isDescriptionVisible())
    }

    @Test
    fun isDescriptionVisible_titleIsNotEmpty_returnTrue(){

        val article = Article(0, "", "", "some description")

        val articleViewModel = ArticleViewModel()
        articleViewModel.setArticle(article)

        assertTrue(articleViewModel.isDescriptionVisible())
    }
}