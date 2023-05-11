package com.abaferastech.marvelapp.ui.comic.comicDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
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

    private val args: ComicDetailsFragmentArgs by navArgs()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }



    private fun init() {
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        adapter = ComicFragmentPageAdapter(requireActivity().supportFragmentManager,lifecycle,args.comicId)
        viewModel.getSingleComic(args.comicId)
        tabLayout.apply {
            addTab(tabLayout.newTab().setText("Characters"))
            addTab(tabLayout.newTab().setText("Series"))
            addTab(tabLayout.newTab().setText("Details"))
        }
        viewPager.adapter = adapter
        initTapLayout()
        initViewPager()
    }

    private fun initTapLayout() {
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let{
                    viewPager.currentItem = it.position
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
