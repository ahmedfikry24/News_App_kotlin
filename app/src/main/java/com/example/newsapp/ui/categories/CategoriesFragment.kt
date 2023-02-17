package com.example.newsapp.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.api.ApiConstant
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.models.SourcesItem
import com.example.newsapp.api.models.SourcesResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesFragment : Fragment() {
    lateinit var progressBar: ProgressBar
    lateinit var errorLayout:LinearLayout
    lateinit var errorText:TextView
    lateinit var errorButton:Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        getTabsSources()
    }

    private fun init(view: View) {
        progressBar = view.findViewById(R.id.categories_progress_bar)
        errorLayout = view.findViewById(R.id.category_error_layout)
        errorText = view.findViewById(R.id.category_error_text)
        errorButton = view.findViewById(R.id.category_error_button)
    }

    private fun getTabsSources() {
        showProgressBar()
        ApiManager.getApi()
            .getSources(ApiConstant.apiKey)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible = false
                    if (response.isSuccessful){
                        Log.e("ahmed","vbnmasdfgh")
                       bindingTabsResponse(response.body()?.sources)
                    }else{
                        val errorMessage =
                            Gson().fromJson(response.errorBody()?.string(), SourcesResponse::class.java).message
                        showErrorLayout(errorMessage)
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    progressBar.isVisible = false
                    showErrorLayout(t.localizedMessage)
                }

            })

    }

    fun bindingTabsResponse(response: List<SourcesItem?>?) {

    }

    fun showErrorLayout(errorMessage: String?) {
        errorLayout.isVisible = true
        errorText.text = errorMessage
    }
  fun  showProgressBar(){
      progressBar.isVisible = true
      errorLayout.isVisible = false
  }
}