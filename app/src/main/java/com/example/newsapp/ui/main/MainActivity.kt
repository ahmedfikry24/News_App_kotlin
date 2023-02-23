import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.newsapp.R
import com.example.newsapp.api.ApiConstant
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.models.SourcesItem
import com.example.newsapp.api.models.SourcesResponse
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var tabLayout: TabLayout
    private lateinit var errorLayout: LinearLayout
    private lateinit var errorText: TextView
    private lateinit var errorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getTabsSources()
    }

    private fun initViews() {
        progressBar = findViewById(R.id.categories_progress_bar)
        tabLayout = findViewById(R.id.tabs_layout)
        errorLayout = findViewById(R.id.category_error_layout)
        errorText = findViewById(R.id.category_error_text)
        errorButton = findViewById(R.id.category_error_button)
    }
    private fun getTabsSources() {
        showProgressBar()
        ApiManager.getApi()
            .getSources(ApiConstant.apiKey)
            .enqueue( object : Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    progressBar.isVisible = false
                    if (response.isSuccessful){
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
        response?.forEach{
            val tab = tabLayout.newTab()
            tab.text = it?.name
            tabLayout.addTab(tab)
            tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }
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