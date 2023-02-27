package com.example.newsapp.ui.fragments.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Adapters.ArticlesAdapter
import com.example.newsapp.R
import com.example.newsapp.api.models.Tab
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.fragments.categories.Category
import com.google.android.material.tabs.TabLayout

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val adapter = ArticlesAdapter(listOf())
    private lateinit var newsViewModel: NewsViewModel
    var category: Category? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_news,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        initViews()
        initListeners()
        newsViewModel.getTabsSources()
        observeViewModel()
    }

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val newsFragment = NewsFragment()
            newsFragment.category = category
            return newsFragment
        }
    }

    private fun observeViewModel() {

        newsViewModel.tabsLiveData.observe(viewLifecycleOwner) {
            bindingTabsResponse(it)
        }
        newsViewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            binding.categoriesProgressBar.isVisible = it
        }
        newsViewModel.errorLayoutLiveData.observe(viewLifecycleOwner) {
            binding.categoryErrorLayout.isVisible = it
        }
        newsViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            showErrorLayout(it)
        }
        newsViewModel.articlesLiveData.observe(viewLifecycleOwner) {
            adapter.changeDate(it)
        }
    }

    private fun initViews() {
        binding.articlesRecylcerView.adapter = adapter
    }

    private fun initListeners() {
        binding.tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                newsViewModel.getArticles(tab?.tag as String)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                newsViewModel.getArticles(tab?.tag as String)
            }

        })

    }


    private fun bindingTabsResponse(response: List<Tab?>?) {
        response?.forEach {
            val tab = binding.tabsLayout.newTab()
            tab.text = it?.name
            tab.tag = it?.id
            binding.tabsLayout.addTab(tab)
        }
    }

    private fun showErrorLayout(errorMessage: String?) {
        binding.categoryErrorLayout.isVisible = true
        binding.categoryErrorText.text = errorMessage
    }
}