package com.aaks.news

import com.aaks.news.model.Article
import org.junit.Assert

abstract class BaseArticleRepositoryTests {

    protected fun assertAreArticlesEqual(expected: Article, actual: Article){
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.url, actual.url)
        Assert.assertEquals(expected.title, actual.title)
        Assert.assertEquals(expected.description, actual.description)
    }

    protected fun buildArticle(tag: String) : Article {
        return Article(0, "https://article${tag}.com", "$tag title", "$tag description")
    }
}