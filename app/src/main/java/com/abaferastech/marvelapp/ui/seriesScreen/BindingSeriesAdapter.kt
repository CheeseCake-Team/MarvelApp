package com.abaferastech.marvelapp.ui.seriesScreen

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.Series

@BindingAdapter(value = ["app:items"])
fun setRecyclerViewItems(view:RecyclerView,items:List<Series>?){
    if (items != null){
        (view.adapter as SeriesAdapter).setItems(items)
    }
    else{
        (view.adapter as SeriesAdapter).setItems(emptyList())
    }
}