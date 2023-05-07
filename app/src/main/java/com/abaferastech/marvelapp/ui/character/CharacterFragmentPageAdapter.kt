package com.abaferastech.marvelapp.ui.character

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.eventScreen.EventsFragment

class CharacterFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)

            ComicsViewAllHorizontalFragment()
        else
            EventsFragment()
    }
}