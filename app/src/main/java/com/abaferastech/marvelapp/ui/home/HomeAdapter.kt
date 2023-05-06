package com.abaferastech.marvelapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.Tag
import com.abaferastech.marvelapp.databinding.ItemHeaderBinding
import com.abaferastech.marvelapp.databinding.ItemTagBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.characters.CharactersAdapter
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener

private const val HEADER_ITEM = 0
private const val TAG_ITEM = 1

class HomeAdapter(private val items: List<DataItem>, listener: ComicsInteractionListener) :
    RecyclerView.Adapter<BaseAdapter.ItemViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DataItem.HeaderItem -> HEADER_ITEM
            else -> TAG_ITEM
        }
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapter.ItemViewHolder {
        return when (viewType) {
            HEADER_ITEM -> {
                BaseAdapter.ItemViewHolder(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }

            TAG_ITEM -> {
                BaseAdapter.ItemViewHolder(
                    ItemTagBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }

            else -> throw java.lang.ClassCastException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseAdapter.ItemViewHolder, position: Int) {
        when (holder.binding) {
            is ItemTagBinding -> {
                when (items[position]) {
                    is DataItem.ComicsTagItem -> {
                        val adapter =
                            ComicAdapter((items[position] as DataItem.ComicsTagItem).tag.ResourcesData,
                                object : ComicsInteractionListener {
                                    override fun onClickSeries(Comics: Comics) {

                                    }
                                })
                        holder.binding.tagItem = (items[position] as DataItem.ComicsTagItem).tag
                        holder.binding.recyclerViewTagData.adapter = adapter
                    }

                    is DataItem.SeriesTagItem -> {
                        val adapter =
                            SeriesAdapter((items[position] as DataItem.SeriesTagItem).tag.ResourcesData,
                                object : SeriesInteractionListener {
                                    override fun onClickSeries(series: Series) {

                                    }
                                })
                        holder.binding.tagItem = (items[position] as DataItem.SeriesTagItem).tag
                        holder.binding.recyclerViewTagData.adapter = adapter
                    }

                    else -> {
                        val adapter =
                            CharactersAdapter((items[position] as DataItem.CharacterTagItem).tag.ResourcesData,
                                object : CharactersInteractionListener {
                                    override fun onCharacterClick(character: Characters) {

                                    }
                                })
                        holder.binding.tagItem = (items[position] as DataItem.CharacterTagItem).tag
                        holder.binding.recyclerViewTagData.adapter = adapter
                    }
                }
            }

            is ItemHeaderBinding -> {
                (holder.binding).character = (items[position] as DataItem.HeaderItem).item
            }
        }
    }

}

sealed class DataItem() {
    abstract val rank: Int

    data class ComicsTagItem(val tag: Tag<Comics>, override val rank: Int) : DataItem()

    data class CharacterTagItem(val tag: Tag<Characters>, override val rank: Int) : DataItem()

    data class SeriesTagItem(val tag: Tag<Series>, override val rank: Int) : DataItem()
    data class HeaderItem(val item: Characters, override val rank: Int) : DataItem()
}