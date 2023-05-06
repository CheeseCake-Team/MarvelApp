package com.abaferastech.marvelapp.ui.comics


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.ui.eventScreen.EventAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun imageUrl(imageView: ImageView, path: String?){
    path.let {
        Glide.with(imageView.context).load(path).into(imageView)
    }
}

@BindingAdapter(value = ["app:items"])
fun setRecyclerViewItems(view: RecyclerView, items: List<Comics>?) {

    if (items != null) {
        (view.adapter as ComicsAdapter).setItems(items)
    } else {
        (view.adapter as ComicsAdapter).setItems(emptyList())
    }
}