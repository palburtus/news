package com.aaks.news.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.aaks.news.R
import com.aaks.news.subscriptions.SubscribeActivity

class MainActivity : AppCompatActivity(){

    private lateinit var textMessage: TextView
    private lateinit var buttonSubscribe: Button

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                textMessage.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)

        buttonSubscribe = findViewById(R.id.button_subscribe)
        buttonSubscribe.setOnClickListener {
            val intent = Intent(applicationContext, SubscribeActivity::class.java)
            startActivity(intent)
        }

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


}
