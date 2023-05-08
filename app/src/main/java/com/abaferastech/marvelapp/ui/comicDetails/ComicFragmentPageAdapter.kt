package com.abaferastech.marvelapp.ui.comicDetails

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.eventScreen.EventsFragment

class ComicFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
    private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            ComicsViewAllHorizontalFragment.newInstance(id)
        else {
            EventsFragment.newInstance(id)
        }
    }
}