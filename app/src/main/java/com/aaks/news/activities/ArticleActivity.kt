package com.aaks.news.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aaks.news.R
import com.aaks.news.databinding.ActivityArticleBinding
import com.aaks.news.model.Article

import com.aaks.news.mvvm.ArticleViewModel

class ArticleActivity : AppCompatActivity() {

    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ArticleViewModel()

        val binding: ActivityArticleBinding = DataBindingUtil.setContentView(this, R.layout.activity_article)
        binding.viewModel = viewModel

        viewModel.article.observe(this, Observer {  })

        val article = Article(1, "http://webmd.com/nothing", "Article Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Felis eget velit aliquet sagittis id consectetur. Vestibulum sed arcu non odio euismod lacinia at. Integer eget aliquet nibh praesent tristique magna sit. Blandit volutpat maecenas volutpat blandit aliquam etiam erat velit. Cursus turpis massa tincidunt dui ut ornare lectus sit. Nibh ipsum consequat nisl vel. Venenatis a condimentum vitae sapien pellentesque habitant morbi. Pellentesque pulvinar pellentesque habitant morbi tristique senectus et. Vulputate sapien nec sagittis aliquam malesuada bibendum arcu vitae elementum. Proin sagittis nisl rhoncus mattis. Volutpat lacus laoreet non curabitur gravida arcu ac tortor dignissim. Tellus orci ac auctor augue mauris augue neque gravida in. Cursus eget nunc scelerisque viverra. In nulla posuere sollicitudin aliquam ultrices. Tristique senectus et netus et malesuada fames.Malesuada bibendum arcu vitae elementum curabitur vitae nunc sed. In vitae turpis massa sed elementum tempus egestas sed. Purus non enim praesent elementum. Mi ipsum faucibus vitae aliquet. In dictum non consectetur a erat nam at lectus. Mi tempus imperdiet nulla malesuada pellentesque. Eros in cursus turpis massa tincidunt dui ut. Id interdum velit laoreet id. Dolor sit amet consectetur adipiscing elit. Mattis nunc sed blandit libero. Bibendum at varius vel pharetra vel turpis nunc eget. Diam in arcu cursus euismod quis viverra nibh cras pulvinar. Egestas erat imperdiet sed euismod nisi porta. Elementum sagittis vitae et leo duis ut diam quam. Laoreet non curabitur gravida arcu ac tortor dignissim convallis. Est ullamcorper eget nulla facilisi etiam dignissim diam quis enim. Auctor elit sed vulputate mi sit. Imperdiet massa tincidunt nunc pulvinar sapien et ligula.")
        viewModel.setArticle(article)

    }
}
