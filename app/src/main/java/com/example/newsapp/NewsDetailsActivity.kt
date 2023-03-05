package com.example.newsapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.api.models.Article

class NewsDetailsActivity : AppCompatActivity() {
    private var article:String? = null
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        initViews()
    }

    private fun initViews(){
       article = intent.getStringExtra("content")
        println(article)
    }
}