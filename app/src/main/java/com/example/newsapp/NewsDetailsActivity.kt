package com.example.newsapp

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.api.models.Article

class NewsDetailsActivity : AppCompatActivity() {
    var article:Article? = null
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        initViews()
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun initViews(){
       article = intent.getSerializableExtra("article",Article::class.java)
    }
}