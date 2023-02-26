package com.example.newsapp.ui.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.ApiConstant
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.models.Article
import com.example.newsapp.api.models.ArticlesResponse
import com.example.newsapp.api.models.Tab
import com.example.newsapp.api.models.TabsResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val tabsLiveData = MutableLiveData<List<Tab?>?>()
    val progressBarLiveData = MutableLiveData<Boolean>()
    val errorLayoutLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()
    val articlesLiveData = MutableLiveData<List<Article?>>()

    fun getTabsSources() {
        errorLayoutLiveData.value = false
        progressBarLiveData.value = true
        ApiManager.getApi()
            .getSources(ApiConstant.apiKey)
            .enqueue(object : Callback<TabsResponse> {
                override fun onResponse(
                    call: Call<TabsResponse>,
                    response: Response<TabsResponse>
                ) {
                    progressBarLiveData.value = false
                    if (response.isSuccessful) {
                        tabsLiveData.value = response.body()?.tabs!!

                    } else {
                        val errorMessage =
                            Gson().fromJson(
                                response.errorBody()?.string(),
                                TabsResponse::class.java
                            ).message
                        errorMessageLiveData.value = errorMessage!!
                    }
                }

                override fun onFailure(call: Call<TabsResponse>, t: Throwable) {
                    progressBarLiveData.value = false
                    errorMessageLiveData.value = t.localizedMessage
                }

            })

    }


    fun getArticles(id: String) {
        progressBarLiveData.value = true
        ApiManager.getApi().getArticles(ApiConstant.apiKey, id)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    progressBarLiveData.value = false
                    articlesLiveData.value = response.body()?.articles!!
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    progressBarLiveData.value = false
                }

            })
    }
}