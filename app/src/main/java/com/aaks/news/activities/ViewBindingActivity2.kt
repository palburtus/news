package com.aaks.news.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.aaks.news.R
import com.aaks.news.databinding.ActivityViewBinding2Binding

class ViewBindingActivity2 : AppCompatActivity() {

    private lateinit var binding:ActivityViewBinding2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val textView = binding.resultView
        val inputData = intent.extras
        inputData?.let {
            val input = it.getString("input")
            textView.text = input
        }
    }

    private fun initView() {
        val layoutInflater = LayoutInflater.from(this)
        binding = ActivityViewBinding2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
