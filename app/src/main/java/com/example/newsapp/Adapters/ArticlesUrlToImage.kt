package com.example.newsapp.Adapters

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ArticlesUrlToImage(image: ImageView, url: String?) {

    if (url.isNullOrBlank()) return

    Glide.with(image)
        .load(url)
        .into(image)
}
@BindingAdapter("launchUrl")
fun launchUrl(view : LinearLayout , url : String) {
    if (url.isBlank()) return

    view.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW , Uri.parse(url))
       view.context.startActivity(intent)
    }
}