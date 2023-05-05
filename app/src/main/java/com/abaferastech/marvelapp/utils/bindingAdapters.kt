package com.abaferastech.marvelapp.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.ui.comics.ComicsAdapter


@BindingAdapter(value=["app:items"])
fun setRecyclerItems(view:RecyclerView,items:List<Comics>?){
    if(items!=null){
        (view.adapter as ComicsAdapter).setItems(items)
    }else{
        (view.adapter as ComicsAdapter).setItems(emptyList())
    }

}