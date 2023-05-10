package com.abaferastech.marvelapp.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.data.model.response.Thumbnail
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.characters.CharactersAdapter
import com.abaferastech.marvelapp.ui.home.HomeFragmentDirections
import com.abaferastech.marvelapp.ui.home.adapters.ComicAdapter
import com.abaferastech.marvelapp.ui.home.adapters.SeriesAdapter
import com.abaferastech.marvelapp.ui.model.UIState
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imageUrl"])
fun imageUrl (view: ImageView, thumbnail: Thumbnail?){
    Glide.with(view)
        .load("${thumbnail?.path}.${thumbnail?.extension}")
        .into(view)
}



@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}


@BindingAdapter("app:tagTitle")
fun setTagTitle(view: TextView, dataItem: DataItem) {
    view.text = when (dataItem) {
        is DataItem.ComicsTagItem -> {
            dataItem.tag.title
        }
        is DataItem.CharacterTagItem -> {
            dataItem.tag.title
        }
        is DataItem.SeriesTagItem -> {
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


@BindingAdapter("onClickCharacter")
fun View.setClickCharacter(characterId: Int?) {
    setOnClickListener {
        val action = HomeFragmentDirections.actionHomeFragmentToCharacterFragment(characterId!!)
        findNavController().navigate(action)
    }
}

@BindingAdapter("app:showWhenLoading")
fun <T> showWhenLoading(view: View, state: UIState<T>?) {
    if (state is UIState.Loading) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}
