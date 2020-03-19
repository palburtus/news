package com.aaks.news.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.aaks.news.R
import com.aaks.news.mvvm.ArticleViewModel

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val viewModel by viewModels<ArticleViewModel>()
    }
}
