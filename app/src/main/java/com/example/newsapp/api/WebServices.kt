package com.example.newsapp.api

import com.example.newsapp.api.models.ArticlesResponse
import com.example.newsapp.api.models.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKey:String):Call<SourcesResponse>

    @GET("v2/everything")
    fun getArticles(@Query("apiKey") apiKey:String , @Query("sources") Id :String):Call<ArticlesResponse>
}