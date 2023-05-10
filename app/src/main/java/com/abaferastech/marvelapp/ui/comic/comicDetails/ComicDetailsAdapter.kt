//package com.abaferastech.marvelapp.ui.comicDetails
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager2.widget.ViewPager2
//import com.abaferastech.marvelapp.data.model.uimodel.DataItem
//import com.abaferastech.marvelapp.databinding.FragmentCharacterBinding
//import com.abaferastech.marvelapp.databinding.ItemHeaderDetailsBinding
//import com.abaferastech.marvelapp.ui.base.BaseAdapter
//import com.abaferastech.marvelapp.ui.characterDetails.CharacterFragmentPageAdapter
//import com.abaferastech.marvelapp.ui.home.adapters.NavigationInteractionListener
//import com.google.android.material.tabs.TabLayout
//
//private const val HEADER_ITEM = 0
//private const val TAG_ITEM = 1
//
////interface NavigationInteractionListener {
////    fun onNavigate(dataItem: DataItem)
////}
//
//class ComicDetailsAdapter(
//    private val items: List<DataItem>,
//    private val navigationListener: NavigationInteractionListener,
//    private val owner: ComicDetailsFragment
//) :
//    RecyclerView.Adapter<BaseAdapter.ItemViewHolder>() {
//
//    override fun getItemViewType(position: Int): Int {
//        return when (items[position]) {
//            is DataItem.TabItem -> TAG_ITEM
//            is DataIComicsViewAllFragmenttem.HeaderDetailsItem -> HEADER_ITEM
//            else -> {
//                10
//            }
//        }
//    }
//
//    override fun getItemCount() = items.size
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): BaseAdapter.ItemViewHolder {
//        return when (viewType) {
//            HEADER_ITEM -> {
//                BaseAdapter.ItemViewHolder(
//                    ItemHeaderDetailsBinding.inflate(
//                        LayoutInflater.from(parent.context),
//                        parent, false
//                    )
//                )
//            }
//
//            TAG_ITEM -> {
//                BaseAdapter.ItemViewHolder(
//                    FragmentCharacterBinding.inflate(
//                        LayoutInflater.from(parent.context),
//                        parent, false
//                    )
//                )
//            }
//
//            else -> throw java.lang.ClassCastException("Unknown view type: $viewType")
//        }
//    }
//
//    override fun onBindViewHolder(holder: BaseAdapter.ItemViewHolder, position: Int) {
//        when (holder.binding) {
//            is ItemHeaderDetailsBinding -> {
//                with(holder.binding) {
//                    comic = (items[position] as DataItem.HeaderDetailsItem).items
//                }
//            }
//
//            is FragmentCharacterBinding -> {
//                holder.binding.apply {
//                    holder.binding.viewPager.adapter = CharacterFragmentPageAdapter(
//                        owner.requireActivity().supportFragmentManager,
//                        owner.lifecycle,null
//                    )
//                    tabLayout.apply {
//                        addTab(tabLayout.newTab().setText("All Comics"))
//                        addTab(tabLayout.newTab().setText("Details"))
//                    }
//                    tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//                        override fun onTabSelected(tab: TabLayout.Tab?) {
//                            if (tab != null) {
//                                viewPager.currentItem = tab.position
//                            }
//                        }
//                        override fun onTabUnselected(tab: TabLayout.Tab?) {
//                        }
//                        override fun onTabReselected(tab: TabLayout.Tab?) {
//                        }
//                    })
//                    viewPager.registerOnPageChangeCallback(object :
//                        ViewPager2.OnPageChangeCallback() {
//                        override fun onPageSelected(position: Int) {
//                            super.onPageSelected(position)
//                            tabLayout.selectTab(tabLayout.getTabAt(position))
//                        }
//                    })
//                }
//
//
//            }
//        }
//    }
//
//
//}
//
//
