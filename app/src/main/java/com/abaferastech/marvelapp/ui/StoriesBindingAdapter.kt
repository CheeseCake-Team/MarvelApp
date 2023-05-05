package com.abaferastech.marvelapp.ui


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun imageUrl(imageView: ImageView, path: String?){
    path.let {
        Glide.with(imageView.context).load(path).into(imageView)
    }
}