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
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.characters.CharactersAdapter
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.comic.comics.ComicsAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.events.EventAdapter
import com.abaferastech.marvelapp.ui.events.EventsInteractionListener
import com.abaferastech.marvelapp.ui.home.HomeFragmentDirections
import com.abaferastech.marvelapp.ui.home.adapters.ComicAdapter
import com.abaferastech.marvelapp.ui.home.adapters.SeriesAdapter
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.SearchItem
import com.abaferastech.marvelapp.ui.model.TYPE
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

@BindingAdapter(value = ["app:setSearchRecyclerViewItems"])
fun setSearchRecyclerViewItems(view: RecyclerView, items: SearchItem?) {
    items.let {
        when (items) {
            is SearchItem.Character -> {
                (view.adapter as CharactersAdapter?)?.setItems(items.items)
            }
            is SearchItem.Event -> {
                (view.adapter as EventAdapter?)?.setItems(items.items)
            }
            is SearchItem.Series -> {
                (view.adapter as SeriesAdapter?)?.setItems(items.items)
            }
            is SearchItem.Comic -> {
                (view.adapter as ComicsAdapter?)?.setItems(items.items)
            }
            else -> {

            }
        }
    }
}

@BindingAdapter(value = ["app:setAdapterByType"])
fun setAdapterByType(view: RecyclerView, type: TYPE?) {
    type.let {
        val adapter = when (it){
            TYPE.CHARACTER -> {
                CharactersAdapter(emptyList(), object : CharactersInteractionListener {
                    override fun onClickCharacter(character: Characters) {

                    }
                })
            }
            TYPE.EVENT -> {
                EventAdapter(emptyList(), object : EventsInteractionListener {
                    override fun onEventClick(event: Events) {

                    }
                })
            }
            TYPE.SERIES -> {
                SeriesAdapter(emptyList(), object : SeriesInteractionListener {
                    override fun onClickSeries(series: Series) {

                    }
                })
            }
            else -> {
                ComicsAdapter(emptyList(), object : ComicsInteractionListener {})
            }
        }
        view.adapter= adapter
    }
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
fun <T> showWhenLoading(view: View, uiState: UIState<T>?) {
    if (uiState is UIState.Loading) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

@BindingAdapter("app:showWhenSuccess")
fun <T> showWhenSuccess(view: View, UiState: UIState<T>?) {
    if (UiState is UIState.Success) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}