package com.example.newsapp.ui.fragments

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
        Category("sports" , "Sports",R.drawable.ball , R.color.purple_200 , true),
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
    }


  private  fun iniViews(view: View){
      recyclerView = view.findViewById(R.id.main_recycler_categories)
      recyclerView.adapter = adapter
    }
}