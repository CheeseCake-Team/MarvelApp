package com.abaferastech.marvelapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

interface BaseInteractionListener

abstract class BaseAdapter<T>(
    private var items: List<T>,
    private val listener: BaseInteractionListener?
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    abstract val layoutID: Int

    abstract fun bindItemViewHolder(holder: BaseViewHolder, item: T, listener: BaseInteractionListener?)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutID,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        bindItemViewHolder(holder, currentItem, listener)
    }

    fun setItems(newItems: List<T>) {
        val diffUtil = DiffUtil.calculateDiff(DiffUtils(items, newItems))
        items = newItems
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = items.size

    fun getItems() = items

    abstract class BaseViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    class ItemViewHolder(binding: ViewDataBinding) : BaseViewHolder(binding)

    inner class DiffUtils<T>(private val oldList: List<T>, private val newList: List<T>) :
        DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
    }
}