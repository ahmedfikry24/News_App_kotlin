package com.example.newsapp.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.models.SourcesResponse
import com.example.newsapp.ui.categories.CategoriesFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val categoriesFragment = CategoriesFragment()
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     supportFragmentManager.beginTransaction()
         .replace(R.id.category_fragment_container ,categoriesFragment)
         .commit()
    }
}