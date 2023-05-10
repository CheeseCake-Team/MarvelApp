package com.abaferastech.marvelapp.ui.comic.comicDetails

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout

class ComicDetailsFragment :
    BaseFragment<FragmentComicDetailsBinding, ComicDetailsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_comic_details

    override val viewModelClass: Class<ComicDetailsViewModel>
        get() = ComicDetailsViewModel::class.java


    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ComicFragmentPageAdapter


    val comicId = 1003
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSingleComic(comicId)
        init()
    }

    private fun init() {
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        adapter = ComicFragmentPageAdapter(requireActivity().supportFragmentManager,lifecycle,comicId)
        tabLayout.apply {
            addTab(tabLayout.newTab().setText("All Comics"))
            addTab(tabLayout.newTab().setText("Details"))
        }
        viewPager.adapter = adapter
        initTapLayout()
        initViewPager()
    }

    private fun initTapLayout() {
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun initViewPager() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

}
