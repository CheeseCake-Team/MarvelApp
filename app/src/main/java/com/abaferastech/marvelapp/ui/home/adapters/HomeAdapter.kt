package com.abaferastech.marvelapp.ui.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.abaferastech.marvelapp.databinding.ItemHeaderViewPagerBinding
import com.abaferastech.marvelapp.databinding.ItemTagBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.character.characters.CharactersAdapter
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.model.DataItem
import com.zhpan.indicator.enums.IndicatorStyle

private const val HEADER_ITEM = 0
private const val TAG_ITEM = 1

interface NavigationInteractionListener : BaseInteractionListener {
    fun onNavigate(id: String)
}

class HomeAdapter(
    private var homeItems: MutableList<DataItem>,
    val baselistener: BaseInteractionListener,
) : BaseAdapter<DataItem>(baselistener) {

    override fun getItemViewType(position: Int): Int {
        return when (homeItems[position]) {
            is DataItem.HeaderItem -> HEADER_ITEM
            else -> TAG_ITEM
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
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }

            TAG_ITEM -> {
                ItemViewHolder(
                    ItemTagBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
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
                    val adapter = HeaderAdapter((homeItems[position] as DataItem.HeaderItem).items)
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
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int
                            ) {
                                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                                pageIndicatorView.onPageScrolled(
                                    position,
                                    positionOffset,
                                    positionOffsetPixels
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
            val item = homeItems[position]
            when (item) {
                is DataItem.ComicsTagItem -> {
                    (holder.binding as ItemTagBinding).recyclerViewTagData.adapter = ComicAdapter(
                        item.tag.ResourcesData,
                        baselistener as ComicsInteractionListener
                    )
                    holder.binding.tagItem = item.tag

                }
                is DataItem.SeriesTagItem -> {
                    (holder.binding as ItemTagBinding).recyclerViewTagData.adapter = SeriesAdapter(
                        item.tag.ResourcesData,
                        baselistener as SeriesInteractionListener
                    )
                    holder.binding.tagItem = item.tag

                }
                is DataItem.CharacterTagItem -> {
                    (holder.binding as ItemTagBinding).recyclerViewTagData.adapter =
                        CharactersAdapter(
                            item.tag.ResourcesData,
                            baselistener as CharactersInteractionListener
                        )
                    holder.binding.tagItem = item.tag

                }

                else -> {
                    Log.d("assd", "onNavigate: ")
                }
            }

        }
    }
}