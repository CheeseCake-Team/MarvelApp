package com.abaferastech.marvelapp.ui.eventScreen

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imageUrl"])
fun setImageFromUrl (view: ImageView, url: String?){

    Glide.with(view).load("$url.jpg").centerCrop().into(view)
}

@BindingAdapter(value = ["app:items"])
fun setRecyclerItems(view: RecyclerView, items: List<Events>?){

    if (items != null){
        (view.adapter as EventAdapter).setItems(items)
    }
    else{
        (view.adapter as EventAdapter).setItems(emptyList())
    }
}