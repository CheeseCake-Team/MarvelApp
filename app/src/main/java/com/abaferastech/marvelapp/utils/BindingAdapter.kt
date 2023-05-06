package com.abaferastech.marvelapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.data.model.response.Thumbnail
import com.abaferastech.marvelapp.ui.eventScreen.EventAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imageUrl"])
fun imageUrl (view: ImageView, thumbnail: Thumbnail?){
    Glide.with(view)
        .load("${thumbnail?.path}.${thumbnail?.extension}")
        .into(view)
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerViewItems(view: RecyclerView, items: List<T>?) {
    items?.let {
        when (view.adapter) {
            is EventAdapter -> {
                (view.adapter as EventAdapter).setItems(items as List<Events>)
            }
        }
    }
}