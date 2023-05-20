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
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.ui.model.SearchItem
import com.abaferastech.marvelapp.ui.model.UIState
import com.bumptech.glide.Glide


@BindingAdapter("app:imageUrl")
fun ImageView.setImageFromUrl(imageUri: String?) {
    val imageUrl = imageUri.takeIf { !it.isNullOrEmpty() && !it.contains("image_not_available") } ?: R.drawable.no_image
    Glide.with(this)
        .load(imageUrl)
        .placeholder(R.drawable.no_image)
        .error(R.drawable.no_image)
        .centerCrop()
        .into(this)
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    items?.let {
        (view.adapter as BaseAdapter<T>?)?.setItems(items)
    }
}

@BindingAdapter(value = ["app:showLoading"])
fun showLoading(view: ProgressBar, isShowing: Boolean) {
    view.isVisible = isShowing
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

@BindingAdapter("app:showWhenEmpty")
fun showWhenEmpty(view: View, empty: Boolean){
    view.isVisible = empty
}

@BindingAdapter("app:showWhenError")
fun <T> showWhenError(view: View, uiState: UIState<T>?) {
    if (uiState is UIState.Error) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
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

