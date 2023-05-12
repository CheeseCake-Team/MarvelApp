package com.abaferastech.marvelapp.ui.character.characterDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.event.events.EventsFragment
import com.abaferastech.marvelapp.ui.model.TYPE

class CharacterFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
    private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){

           0 -> ComicsViewAllHorizontalFragment.newInstance(id,TYPE.CHARACTER)
           1 -> EventsFragment.newInstance(id,TYPE.CHARACTER)
           else -> CharacterDataFragment.newInstance(id,TYPE.CHARACTER)
        }
    }
}