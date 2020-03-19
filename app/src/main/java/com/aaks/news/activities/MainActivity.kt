package com.aaks.news.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.aaks.news.R
import com.aaks.news.subscriptions.SubscribeActivity
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker

class MainActivity : AppCompatActivity(){

    private lateinit var textMessage: TextView
    private lateinit var buttonSubscribe: Button
    private lateinit var buttonSaved: Button
    private lateinit var buttonGeo: Button
    private lateinit var buttonArticle: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //Helper class provided by estimote that handles runtime permissions
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)

        buttonSubscribe = findViewById(R.id.buttonSubscribe)
        buttonSubscribe.setOnClickListener {
            val intent = Intent(applicationContext, SubscribeActivity::class.java)
            startActivity(intent)
        }

        buttonSaved = findViewById(R.id.buttonSaved)
        buttonSaved.setOnClickListener {
            val intent = Intent(applicationContext, SavedActivity::class.java)
            startActivity(intent)
        }

        buttonGeo = findViewById(R.id.buttonGeo)
        buttonGeo.setOnClickListener {
            val intent = Intent(applicationContext, BeaconActivity::class.java)
            startActivity(intent)
        }

        buttonArticle = findViewById(R.id.buttonArticle)
        buttonArticle.setOnClickListener {
            val intent = Intent(applicationContext, ArticleActivity::class.java)
            startActivity(intent)
        }

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

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
}
