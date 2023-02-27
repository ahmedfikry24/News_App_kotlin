package com.example.newsapp.Adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ArticlesUrlToImage(image: ImageView, url: String?) {

    if (url.isNullOrBlank()) return

    Glide.with(image)
        .load(url)
        .into(image)
}