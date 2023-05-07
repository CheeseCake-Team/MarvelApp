package com.abaferastech.marvelapp.ui.seriesViewAll

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.ui.seriesScreen.SeriesAdapter

@BindingAdapter(value = ["app:items"])
fun setRecyclerViewAllItems(view: RecyclerView, items: List<Series>?) {
    if (items != null) {
        (view.adapter as SeriesViewAllAdapter).setItems(items)
    } else {
        (view.adapter as SeriesViewAllAdapter).setItems(emptyList())
    }
}