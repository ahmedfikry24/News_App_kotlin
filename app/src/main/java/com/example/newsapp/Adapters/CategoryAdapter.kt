package com.example.newsapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapp.R
import com.example.newsapp.ui.fragments.categories.Category

class CategoryAdapter(var items: List<Category>) : Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(if (viewType == 1) R.layout.left_item_category else R.layout.right_item_category , parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
       val item = items[position]
        holder.title.text = item.title
        holder.image.setImageResource(item.imageId)
        holder.itemView.setBackgroundColor(holder.itemView.context.getColor(item.color))
        holder.itemView.setOnClickListener {
            onCategoryItemClick?.onClick(item)
        }
    }
var onCategoryItemClick : OnCategoryItemClick? = null
    interface OnCategoryItemClick {
        fun onClick(category: Category)
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return if (item.isLeft) 1 else 2
    }

    override fun getItemCount(): Int = items.size

    class CategoryViewHolder(view: View) : ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_image)
        val title: TextView = view.findViewById(R.id.item_text)
    }
}