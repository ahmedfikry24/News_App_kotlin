package com.example.newsapp.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.api.models.Article

class ArticlesAdapter (var items : List<Article?>) : Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.articles_item , parent ,false)

      return  ArticlesViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val item = items[position]
        holder.author.text = item?.author
        holder.description.text = item?.description
        holder.date.text = item?.publishedAt
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.image)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeDate (newList : List<Article?>) {
        items = newList
        notifyDataSetChanged()
    }

    class ArticlesViewHolder(item:View) : ViewHolder(item){
        val image : ImageView = item.findViewById(R.id.articlesImageView)
        val author : TextView = item.findViewById(R.id.authorTextView)
        val description : TextView = item.findViewById(R.id.descriptionTextView)
        val date : TextView = item.findViewById(R.id.dateTextView)

    }
}