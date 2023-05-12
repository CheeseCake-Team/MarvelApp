package com.abaferastech.marvelapp.ui.creator.creatorsDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.event.events.EventsFragment
import com.abaferastech.marvelapp.ui.model.TYPE

class CreatorFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
    private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CreatorDataFragment.newInstance(id, TYPE.CREATOR)
            1 -> ComicsViewAllHorizontalFragment.newInstance(id, TYPE.CREATOR)
            else -> EventsFragment.newInstance(id, TYPE.CREATOR)
        }
    }
}