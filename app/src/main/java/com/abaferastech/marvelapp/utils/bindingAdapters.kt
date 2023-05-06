package com.abaferastech.marvelapp.utils


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.response.Thumbnail
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.ui.creators.CreatorAdapters
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:showWhenLoading"])
fun<T> showWhenLoading(view: View, state: State<T>?){
   if(state is State.Loading){
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun<T> showWhenError(view: View, state: State<T>?){
 if(state is State.Error){
        view.visibility = View.VISIBLE
    } else {
       view.visibility = View.GONE
   }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun<T> showWhenSuccess(view: View, state: State<T>?){
    if(state is State.Success){
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

//@BindingAdapter(value = ["app:ImageUrl"])
//fun setImageFromUrl(view: ImageView, url: String){
//    Glide.with(view)
//        .load("${thumbnail?.path}.${thumbnail?.extension}")
//        .centerCrop()
//        .into(view)
//}
@BindingAdapter (value = ["app:items"])
fun setRecyclerItems(view: RecyclerView, items: List<Series>){
    if(items != null){
        (view.adapter as CreatorAdapters).setItems(items)
    } else {
        (view.adapter as CreatorAdapters).setItems(emptyList())

    }
}
