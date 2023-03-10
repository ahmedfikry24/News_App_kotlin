package com.example.newsapp.ui.fragments.news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Adapters.ArticlesAdapter
import com.example.newsapp.R
import com.example.newsapp.api.models.Article
import com.example.newsapp.api.models.Tab
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.activites.NewsDetailsActivity
import com.example.newsapp.ui.fragments.categories.Category
import com.google.android.material.tabs.TabLayout

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val adapter = ArticlesAdapter(listOf())
    private lateinit var newsViewModel: NewsViewModel
    var category: Category? = null
    var currentPage = 1
    var firstItemId = ""
    var isLoadArticle = true

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
        newsViewModel.getTabsSources(category?.id!!)
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
        newsViewModel.isLoadArticle.observe(viewLifecycleOwner){
            isLoadArticle = it
        }
    }

    private fun initViews() {
        binding.articlesRecylcerView.adapter = adapter
    }

    private fun initListeners() {
        binding.tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                firstItemId = tab?.tag as String
                newsViewModel.getArticles(firstItemId, currentPage)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                firstItemId = tab?.tag as String
                newsViewModel.getArticles(firstItemId, currentPage)
            }

        })
        binding.categoryErrorButton.setOnClickListener {
            newsViewModel.getTabsSources(category?.id!!)
        }
        binding.articlesRecylcerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val totalItems = layoutManager.itemCount
                if ( isLoadArticle && totalItems.minus(lastVisibleItem) <= 3) {
                    currentPage++
                    newsViewModel.getArticles(firstItemId, currentPage)
                }
            }
        })
        adapter.onItemClick = object : ArticlesAdapter.OnItemClick {
            override fun onItemClick(article: Article) {
                val intent = Intent(requireContext(),NewsDetailsActivity::class.java)
                intent.putExtra("article" , article)
                startActivity(intent)
            }
        }
    }


    private fun bindingTabsResponse(response: List<Tab?>?) {
        firstItemId = response?.first()?.id!!
        response.forEach {
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