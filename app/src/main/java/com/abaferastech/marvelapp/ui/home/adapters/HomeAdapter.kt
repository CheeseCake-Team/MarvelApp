package com.abaferastech.marvelapp.ui.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.abaferastech.marvelapp.databinding.ItemHeaderViewPagerBinding
import com.abaferastech.marvelapp.databinding.ItemTagBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.model.DataItem
import com.zhpan.indicator.enums.IndicatorStyle

private const val HEADER_ITEM = 0
private const val TAG_ITEM = 1

interface NavigationInteractionListener : BaseInteractionListener {
    fun onNavigate(id: Int)
}

class HomeAdapter(
    private var homeItems: MutableList<DataItem>,
    private val interactionListener: BaseInteractionListener,
) : BaseAdapter<DataItem>(interactionListener) {

    override fun getItemViewType(position: Int): Int {
        return when (homeItems[position]) {
            is DataItem.HeaderItem -> {
                HEADER_ITEM
            }

            else -> {
                TAG_ITEM
            }
        }
    }

    override fun setItems(newItems: List<DataItem>) {
        homeItems = newItems.sortedBy { it.rank }.toMutableList()

        super.setItems(homeItems)
    }

    override val layoutId = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HEADER_ITEM -> {
                ItemViewHolder(
                    ItemHeaderViewPagerBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            TAG_ITEM -> {

                ItemViewHolder(
                    ItemTagBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            else -> throw java.lang.ClassCastException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (homeItems.isNotEmpty()) {
            bind(holder as ItemViewHolder, position)
            when (holder.binding) {
                is ItemTagBinding -> {

                    bind(holder, position)
                }

                is ItemHeaderViewPagerBinding -> {
                    val adapter = HeaderAdapter()
                    adapter.setItems((homeItems[position] as DataItem.HeaderItem).items)
                    holder.binding.apply {
                        viewPagerHeader.adapter = adapter
                        pageIndicatorView.apply {
                            setSliderWidth(120F)
                            setSliderHeight(15F)
                            setIndicatorStyle(IndicatorStyle.DASH)
                            setPageSize(viewPagerHeader.adapter!!.itemCount)
                            notifyDataChanged()
                        }
                        viewPagerHeader.registerOnPageChangeCallback(object :
                            ViewPager2.OnPageChangeCallback() {
                            override fun onPageScrolled(
                                position: Int, positionOffset: Float, positionOffsetPixels: Int
                            ) {
                                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                                pageIndicatorView.onPageScrolled(
                                    position, positionOffset, positionOffsetPixels
                                )
                            }

                            override fun onPageSelected(position: Int) {
                                super.onPageSelected(position)
                                pageIndicatorView.onPageSelected(position)
                            }
                        })
                    }
                }

            }
        }
    }

    private fun bind(holder: ItemViewHolder, position: Int) {
        if (position != -1) {
            when (val item = homeItems[position]) {
                is DataItem.ComicsTagItem -> {
                    with(holder.binding as ItemTagBinding) {
                        recyclerViewTagData.adapter =
                            ComicAdapter(interactionListener as ComicsInteractionListener)
                        tagItem = item.tag
                        listener = interactionListener as NavigationInteractionListener?
                    }
                }

                is DataItem.SeriesTagItem -> {
                    with(holder.binding as ItemTagBinding) {
                        recyclerViewTagData.adapter =
                            SeriesAdapter(interactionListener as SeriesInteractionListener)
                        tagItem = item.tag
                        listener = interactionListener as NavigationInteractionListener?
                    }
                }

                is DataItem.CharacterTagItem -> {
                    with(holder.binding as ItemTagBinding) {
                        recyclerViewTagData.adapter =
                            CharactersAdapter(interactionListener as CharactersInteractionListener)
                        tagItem = item.tag
                        listener = interactionListener as NavigationInteractionListener?
                    }
                }

                else -> {
                    Log.d("assd", "onNavigate: ")
                }
            }
        }
    }


//    private fun bind(holder: ItemViewHolder, position: Int) {
//        if (position != -1) {
//            val item = homeItems[position]
//            when (item) {
//                is DataItem.ComicsTagItem -> {
//                    (holder.binding as ItemTagBinding).recyclerViewTagData.adapter = ComicAdapter(
//                        interactionListener as ComicsInteractionListener
//                    )
//                    holder.binding.tagItem = item.tag
//                    holder.binding.listener = interactionListener as NavigationInteractionListener?
//
//
//                }
//
//                is DataItem.SeriesTagItem -> {
//                    (holder.binding as ItemTagBinding).recyclerViewTagData.adapter = SeriesAdapter(
//
//                        interactionListener as SeriesInteractionListener
//                    )
//                    holder.binding.tagItem = item.tag
//                    holder.binding.listener = interactionListener as NavigationInteractionListener?
//
//
//                }
//
//                is DataItem.CharacterTagItem -> {
//                    (holder.binding as ItemTagBinding).recyclerViewTagData.adapter =
//                        CharactersAdapter(
//                            interactionListener as CharactersInteractionListener
//                        )
//                    holder.binding.tagItem = item.tag
//                    holder.binding.listener = interactionListener as NavigationInteractionListener?
//
//
//                }
//
//                else -> {
//                    Log.d("assd", "onNavigate: ")
//                }
//            }
//
//        }
//    }
}