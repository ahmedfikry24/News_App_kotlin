package com.example.newsapp.ui
import android.os.Bundle
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R

class HomeActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var icMenu : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

initViews()
    }

   fun initViews(){
       drawerLayout = findViewById(R.id.drawer_layout)
       icMenu = findViewById(R.id.ic_menu)
   }


    fun initListeners(){
        icMenu.setOnClickListener {
            drawerLayout.open()
        }
    }
}