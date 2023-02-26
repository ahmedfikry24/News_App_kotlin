package com.example.newsapp.ui.fragments.categories
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Adapters.CategoryAdapter
import com.example.newsapp.R

class CategoriesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter : CategoryAdapter = CategoryAdapter(listOf(
        Category("sports" , "Sports",R.drawable.ball , R.color.red , true),
        Category("entertainment" , "Entertainment",R.drawable.politics , R.color.blue , false),
        Category("health" , "Health",R.drawable.health , R.color.pink , true),
        Category("business" , "Business",R.drawable.bussines , R.color.biage , false),
        Category("technology" , "Technology",R.drawable.environment , R.color.lightBlue , true),
        Category("science" , "Science",R.drawable.science , R.color.lightYellow , false),
        ))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniViews(view)
        initListeners()
    }


  private  fun iniViews(view: View){
      recyclerView = view.findViewById(R.id.main_recycler_categories)
      recyclerView.adapter = adapter
    }

    private fun initListeners(){
        adapter.onCategoryItemClick = object : CategoryAdapter.OnCategoryItemClick{
            override fun onClick(category: Category) {
              onItemClick?.onItemClick(category)
            }
        }
    }

    var onItemClick : OnItemClick? = null
    interface OnItemClick {
        fun onItemClick(category: Category)
    }
}