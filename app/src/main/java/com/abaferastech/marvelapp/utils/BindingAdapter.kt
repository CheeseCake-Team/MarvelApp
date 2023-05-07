package com.abaferastech.marvelapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.DataItem
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.data.model.response.Thumbnail
import com.abaferastech.marvelapp.ui.characters.CharactersAdapter
import com.abaferastech.marvelapp.ui.eventScreen.EventAdapter
import com.abaferastech.marvelapp.ui.home.ComicAdapter
import com.abaferastech.marvelapp.ui.home.SeriesAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imageUrl"])
fun imageUrl (view: ImageView, thumbnail: Thumbnail?){
    Glide.with(view)
        .load("${thumbnail?.path}.${thumbnail?.extension}")
        .fitCenter()
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




@BindingAdapter("app:tagTitle")
fun setTagTitle(view: TextView, dataItem: DataItem) {
    view.text = when (dataItem) {
        is DataItem.ComicsTagItem -> dataItem.tag.title
        is DataItem.CharacterTagItem -> dataItem.tag.title
        is DataItem.SeriesTagItem -> dataItem.tag.title
        else ->  ""

    }
}

@BindingAdapter("app:setAdapter")
fun setAdapter(view: RecyclerView, dataItem: DataItem) {
    view.adapter = when (dataItem) {
        is DataItem.ComicsTagItem -> ComicAdapter(
            dataItem.tag.ResourcesData,
            dataItem.interactionListener
        )
        is DataItem.CharacterTagItem -> CharactersAdapter(
            dataItem.tag.ResourcesData,
            dataItem.interactionListener
        )
        is DataItem.SeriesTagItem -> SeriesAdapter(
            dataItem.tag.ResourcesData,
            dataItem.interactionListener
        )
        else -> TODO()
    }
}
