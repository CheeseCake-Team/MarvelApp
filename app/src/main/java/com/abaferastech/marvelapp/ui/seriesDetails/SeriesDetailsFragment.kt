package com.abaferastech.marvelapp.ui.seriesDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout

class SeriesDetailsFragment: BaseFragment<FragmentSeriesDetailsBinding,SeriesDetailsViewModel>() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: SeriesDetailsFragmentPageAdapter
    override val layoutIdFragment: Int
        get() = R.layout.fragment_series_details
    override val viewModelClass: Class<SeriesDetailsViewModel>
        get() = SeriesDetailsViewModel::class.java

//    private val args: SeriesDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSeriesById(19244)
        init()
    }

    private fun init() {
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        adapter = SeriesDetailsFragmentPageAdapter(
            requireActivity().supportFragmentManager,lifecycle,19244)
        tabLayout.apply {
            addTab(tabLayout.newTab().setText("Characters"))
            addTab(tabLayout.newTab().setText("Creators"))
            addTab(tabLayout.newTab().setText("Comics"))
        }
        viewPager.adapter = adapter
        initTapLayout()
        initViewPager()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.hide()
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.show()
        }
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