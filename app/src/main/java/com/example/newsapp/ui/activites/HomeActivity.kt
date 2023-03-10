package com.example.newsapp.ui.activites

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.api.models.Article
import com.example.newsapp.ui.fragments.categories.CategoriesFragment
import com.example.newsapp.ui.fragments.categories.Category
import com.example.newsapp.ui.fragments.news.NewsFragment
import com.example.newsapp.ui.fragments.settings.SettingsFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var icMenu: ImageView
    private lateinit var categoriesTV: TextView
    private lateinit var settingsTV: TextView
    private val categoriesFragment: CategoriesFragment = CategoriesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        initListeners()
        showFragments(categoriesFragment)
    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        icMenu = findViewById(R.id.ic_menu)
        categoriesTV = findViewById(R.id.categories_tv)
        settingsTV = findViewById(R.id.settings_tv)

    }


    private fun initListeners() {
        icMenu.setOnClickListener {
            drawerLayout.open()
        }

        categoriesTV.setOnClickListener {
            showFragments(categoriesFragment)
            drawerLayout.close()
        }

        settingsTV.setOnClickListener {
            showFragments(SettingsFragment())
            drawerLayout.close()
        }

        categoriesFragment.onItemClick = object : CategoriesFragment.OnItemClick {
            override fun onItemClick(category: Category) {
                showFragments(NewsFragment.getInstance(category))
            }
        }
    }

    private fun showFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }
}