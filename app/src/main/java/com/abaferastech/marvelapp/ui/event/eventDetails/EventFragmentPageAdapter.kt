package com.abaferastech.marvelapp.ui.event.eventDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.character.characters.CharactersFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.creator.creators.CreatorsFragment

import com.abaferastech.marvelapp.ui.series.series.SeriesViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.model.TYPE

class EventFragmentPageAdapter(
    fragmentManager: FragmentManager, lifecycle: androidx.lifecycle.Lifecycle, private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EventDataFragment.newInstance(id, TYPE.EVENT)
            1 -> CharactersFragment.newInstance(id, TYPE.EVENT)
            2 -> CreatorsFragment.newInstance(id, TYPE.EVENT)
            else ->  ComicsViewAllHorizontalFragment.newInstance(id, TYPE.EVENT)

        }
    }
}