package com.example.newsapp.ui.activites

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {
    private var article:String? = null
   lateinit var binding : ActivityNewsDetailsBinding
   lateinit var newsDetailsViewModel: NewsDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_news_details)
        newsDetailsViewModel = ViewModelProvider(this)[NewsDetailsViewModel::class.java]
        setContentView(binding.root)
        initViews()
    }

    private fun initViews(){

       article = intent.getStringExtra("content")
        println(article)
    }
}