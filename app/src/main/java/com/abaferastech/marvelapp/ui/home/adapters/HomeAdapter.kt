package com.abaferastech.marvelapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.databinding.ItemHeaderViewPagerBinding
import com.abaferastech.marvelapp.databinding.ItemTagBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.zhpan.indicator.enums.IndicatorStyle

private const val HEADER_ITEM = 0
private const val TAG_ITEM = 1

interface NavigationInteractionListener: BaseInteractionListener {
    fun onNavigate(dataItem: DataItem)
}

class HomeAdapter(
    private val items: List<DataItem>,
    private val navigationListener: NavigationInteractionListener
) : BaseAdapter<DataItem>(items,navigationListener){
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is DataItem.HeaderItem -> HEADER_ITEM
            else -> TAG_ITEM
        }
    }

    override fun getItemCount() = items.size
    override val layoutID: Int
        get() = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
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
        when (holder.binding) {
            is ItemTagBinding -> {
                with(holder.binding) {
                    dataItem = items[position]
                    listener = navigationListener
                }
            }

            is ItemHeaderViewPagerBinding -> {
                val adapter = HeaderAdapter((items[position] as DataItem.HeaderItem).items)
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
