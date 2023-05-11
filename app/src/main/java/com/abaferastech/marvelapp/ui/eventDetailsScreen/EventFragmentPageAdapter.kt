package com.abaferastech.marvelapp.ui.eventDetailsScreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.eventDetailsScreen.creators.CreatorsOfSingleEvent
import com.abaferastech.marvelapp.ui.eventDetailsScreen.characters.CharactersOfSingleEvent

class EventFragmentPageAdapter (
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
    private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharactersOfSingleEvent.newInstance(id)
            else ->  CreatorsOfSingleEvent.newInstance(id)
        }
    }
}