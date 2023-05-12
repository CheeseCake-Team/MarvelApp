package com.abaferastech.marvelapp.ui.eventDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.eventDetails.creators.CreatorsOfSingleEvent
import com.abaferastech.marvelapp.ui.eventDetails.characters.CharactersOfSingleEvent
import com.abaferastech.marvelapp.ui.eventDetails.series.SeriesViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.model.TYPE

class EventFragmentPageAdapter (
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
    private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CharactersOfSingleEvent.newInstance(id,TYPE.EVENT)
            1 ->  CreatorsOfSingleEvent.newInstance(id)
            2 ->  ComicsViewAllHorizontalFragment.newInstance(id,TYPE.EVENT)
            else -> SeriesViewAllHorizontalFragment.newInstance(id,TYPE.EVENT)
        }
    }
}