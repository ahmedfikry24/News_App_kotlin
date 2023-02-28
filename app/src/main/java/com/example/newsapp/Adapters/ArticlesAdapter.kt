package com.example.newsapp.Adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapp.R
import com.example.newsapp.api.models.Article
import com.example.newsapp.databinding.ArticlesItemBinding

class ArticlesAdapter(var items: List<Article?>) : Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {

        val binding = DataBindingUtil.inflate<ArticlesItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.articles_item,
            parent,
            false
        )
        return ArticlesViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.binding.item = items[position]
        holder.binding.executePendingBindings()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeDate(newList: List<Article?>) {
        items = newList
        notifyDataSetChanged()
    }

    class ArticlesViewHolder(val binding: ArticlesItemBinding) : ViewHolder(binding.root) {

    }
}