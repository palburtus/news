package com.aaks.news.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aaks.news.model.Article

class ArticleViewModel : ViewModel() {

    val article = MutableLiveData<Article>()

    init {
        //article.value = Article(1, "http://www.google.com", "My Title", "Some Description")
    }

    fun setArticle(article: Article){
        this.article.value = article
    }

    fun getArticle(): LiveData<Article> {
        return article
    }

    fun isTitleVisible() : Boolean{

        if(article.value!!.title.isNullOrEmpty()){
            return false
        }

        return true
    }
}