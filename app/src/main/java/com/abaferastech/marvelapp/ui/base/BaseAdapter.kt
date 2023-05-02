package com.abaferastech.marvelapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.abaferastech.marvelapp.data.models.ResourceItem

abstract class BaseAdapter<T: ResourceItem>(
    resourceItemDiffCallBack: BaseDiffUtilCallBack<T>
) : ListAdapter<T, BaseViewHolder>(resourceItemDiffCallBack) {

    abstract val layoutResourceId: Int

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                layoutResourceId, parent, false)
        )

}

abstract class BaseDiffUtilCallBack<T: ResourceItem> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.id == newItem.id

}