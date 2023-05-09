package com.abaferastech.marvelapp.utils

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.data.model.response.Thumbnail
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.characters.CharactersAdapter
import com.abaferastech.marvelapp.ui.home.adapters.ComicAdapter
import com.abaferastech.marvelapp.ui.home.adapters.SeriesAdapter
 import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imageUrl"])
fun imageUrl (view: ImageView, thumbnail: Thumbnail?){
    Glide.with(view)
        .load("${thumbnail?.path}.${thumbnail?.extension}")
        .into(view)
}



@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>) {
    (view.adapter as BaseAdapter<T>).setItems(items)
}



@BindingAdapter("app:tagTitle")
fun setTagTitle(view: TextView, dataItem: DataItem) {
    view.text = when (dataItem) {
        is DataItem.ComicsTagItem -> {
            Log.d("TAG", "setTagTitle:${dataItem.tag} ")
            dataItem.tag.title
        }
        is DataItem.CharacterTagItem -> {
            Log.d("TAG", "setTagTitle:${dataItem.tag} ")
            dataItem.tag.title
        }
        is DataItem.SeriesTagItem -> {
            Log.d("TAG", "setTagTitle:${dataItem.tag} ")
            dataItem.tag.title
        }
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
//        is DataItem.SeriesViewTagItem -> SeriesViewAllAdapter(
//            dataItem.tag.ResourcesData,
//            dataItem.interactionListener
//        )
        else -> TODO()
    }
}
