package com.abaferastech.marvelapp.ui.eventDetailsScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentEventBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicsAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener
import com.google.android.material.tabs.TabLayout


class EventFragment : BaseFragment<FragmentEventBinding, EventViewModel>() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: EventFragmentPageAdapter
    private val args: EventFragmentArgs by navArgs()

    override val layoutIdFragment: Int
        get() = R.layout.fragment_event

    override val viewModelClass: Class<EventViewModel>
        get() = EventViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEventsById(args.eventId)
        init()
        backButton()
    }

    override fun onStart() {
        super.onStart()
        accessTapLayout()
    }

    fun backButton() {
        binding.backButton.setOnClickListener {

            requireActivity().onBackPressed();

        }
    }

    private fun init() {
        tabLayout = binding.eventTabLayout
        viewPager = binding.eventViewPager
        adapter = EventFragmentPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            args.eventId
        )
        tabLayout.apply {
            addTab(tabLayout.newTab().setText("Characters"))
            addTab(tabLayout.newTab().setText("Creators"))
        }
        viewPager.adapter = adapter
        initTapLayout()
        initViewPager()

    }

    private fun initTapLayout() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    private fun accessTapLayout() {
        // Get the activity's toolbar
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)

        // Set the toolbar as the activity's action bar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        // Set the title of the toolbar
        (activity as AppCompatActivity).supportActionBar?.hide()


    }

}