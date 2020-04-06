package com.aaks.news.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.aaks.news.R
import com.aaks.news.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityViewBindingBinding //late initialization during activity creation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        //Accessing the view via binding parameter, no need to initialize it with findViewById
        binding.inputField.setOnClickListener(this)
        binding.switchActivity.setOnClickListener(this)
    }

    private fun initView() {
        //creating LayoutInflater
        val layoutInflater = LayoutInflater.from(this)
        //initializing the view
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.changeText -> binding.inputField.setText("ViewBinding rocks!!")
            R.id.switchActivity -> {
                startActivity(
                    Intent(this, ViewBindingActivity2::class.java)
                        .putExtra("input", binding.inputField.text.toString())
                )
            }
        }
    }
}
