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
        .centerCrop()
        .into(view)
}

@BindingAdapter(value = ["app:items"])
fun setRecyclerViewItems(view: RecyclerView, items: List<Events>?){

    if (items != null){
        (view.adapter as EventAdapter).setItems(items)
    }
    else{
        (view.adapter as EventAdapter).setItems(emptyList())
    }
}