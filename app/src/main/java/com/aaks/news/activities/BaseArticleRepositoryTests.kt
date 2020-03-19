package com.aaks.news.activities

import com.aaks.news.model.Article


abstract class BaseArticleRepositoryTests {


    protected fun buildArticle(tag: String) : Article {
        return Article(0, "https://article${tag}.com", "$tag title", "$tag description")
    }
}