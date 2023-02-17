package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.models.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiKey :String = "50be2066a19f42f8a8a0febf70e90a4a"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiManager.getApi().getSources(apiKey).enqueue(object :Callback<SourcesResponse>{
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>
            ) {

            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

            }

        })
    }
}