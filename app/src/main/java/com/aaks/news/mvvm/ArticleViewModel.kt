package com.aaks.news.mvvm

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aaks.news.model.Article

class ArticleViewModel : ViewModel(){

     val article = MutableLiveData<Article>()

    fun setArticle(article: Article?){
        this.article.value = article
    }

    fun isTitleVisible() : Boolean{
        if(this.article.value == null){
            return false
        }

        if(this.article.value!!.title.isEmpty()){
            return false;
        }

        return true
    }

    fun isDescriptionVisible() : Boolean{
        if(this.article.value == null){
            return false
        }

        if(this.article.value!!.description.isEmpty()){
            return false;
        }

        return true
    }
}