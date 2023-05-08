package com.abaferastech.marvelapp.ui.characterDetails

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.local.SharedPreferencesServicesImpl
import com.abaferastech.marvelapp.databinding.FragmentCharacterBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout

class CharacterFragment : BaseFragment<FragmentCharacterBinding,CharacterViewModel>() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: CharacterFragmentPageAdapter
    override val layoutIdFragment: Int
        get() = R.layout.fragment_character
    override val viewModelClass: Class<CharacterViewModel>
        get() = CharacterViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val id = 1011334
        val id = 1009525
        viewModel.getSingleCharacter(id)
        init()
    }

    private fun init() {
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        adapter = CharacterFragmentPageAdapter(requireActivity().supportFragmentManager,lifecycle,id)
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