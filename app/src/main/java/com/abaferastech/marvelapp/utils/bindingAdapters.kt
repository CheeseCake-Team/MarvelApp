package com.abaferastech.marvelapp.utils


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.ui.creators.CreatorsAdapter
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

@BindingAdapter(value = ["app:ImageUrl"])
fun setImageFromUrl(view: ImageView, url: String){
    Glide.with(view).load(url).centerCrop().into(view)
}
@BindingAdapter (value = ["app:items"])
fun setRecyclerItems(view: RecyclerView, items: List<Comics>){
    if(items != null){
        (view.adapter as CreatorsAdapter).setItems(items)
    } else {
        (view.adapter as CreatorsAdapter).setItems(emptyList())

    }
}
