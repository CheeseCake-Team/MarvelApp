package com.abaferastech.marvelapp.ui.characters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.data.model.response.Thumbnail
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imageUrl"])
fun imageUrl (view: ImageView, thumbnail: Thumbnail?){
    Glide.with(view)
        .load("${thumbnail?.path}.${thumbnail?.extension}")
        .into(view)
}

@BindingAdapter(value = ["app:items"])
fun setRecyclerViewItems(view: RecyclerView, items: List<Characters>?){

    if (items != null){
        (view.adapter as ViewAllCharactersAdapter).setItems(items)
    }
    else{
        (view.adapter as ViewAllCharactersAdapter).setItems(emptyList())
    }
}