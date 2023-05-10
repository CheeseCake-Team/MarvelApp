package com.abaferastech.marvelapp.ui.comic.comicDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.eventScreen.EventsFragment

class ComicFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
    private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            ComicsViewAllHorizontalFragment.newInstance(id)
        else {
            EventsFragment.newInstance(id)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

}