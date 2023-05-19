package com.abaferastech.marvelapp.utils

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.Thumbnail
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.character.characters.CharactersAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsAdapter
import com.abaferastech.marvelapp.ui.event.events.EventAdapter
import com.abaferastech.marvelapp.ui.home.adapters.SeriesAdapter
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.ui.model.SearchItem
import com.abaferastech.marvelapp.ui.model.UIState
import com.bumptech.glide.Glide


@BindingAdapter("app:imageUrl")
fun ImageView.setImageFromUrl(thumbnail: Thumbnail?) {
    thumbnail?.path?.let { path ->
        val imageUrl = if (path.contains("image_not_available")) {
            R.drawable.no_image
        } else {
            "${thumbnail?.path}.${thumbnail?.extension}"
        }
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .centerCrop()
            .into(this)
    }
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:showLoading"])
fun showLoading(view: ProgressBar, isShowing: Boolean) {
    view.isVisible = isShowing
}

@BindingAdapter(value = ["app:setSearchRecyclerViewItems"])
fun setSearchRecyclerViewItems(view: RecyclerView, items: SearchItem?) {
    items.let {
        when (items) {
            is SearchItem.Character -> (view.adapter as CharactersAdapter?)?.setItems(items.items)
            is SearchItem.Event -> (view.adapter as EventAdapter?)?.setItems(items.items)
            is SearchItem.Series -> (view.adapter as SeriesAdapter?)?.setItems(items.items)
            is SearchItem.Comic -> (view.adapter as ComicsAdapter?)?.setItems(items.items)
            else -> {}
        }
    }
}

@BindingAdapter("emptyStateTextView")
fun setEmptyStateTextViewVisibility(
    emptyStateTextView: TextView, searchItem: SearchItem?
) {
    searchItem.let {
        emptyStateTextView.isVisible = searchItem?.itemsList?.isEmpty() == true
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

        else -> ""

    }
}

@BindingAdapter("app:chipGroupVisibility")
fun chipGroupVisibility(horizontalScrollView: HorizontalScrollView, isVisible: Boolean) {
    horizontalScrollView.visibility = if (isVisible) View.VISIBLE else View.GONE
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

