package com.abaferastech.marvelapp.ui.comic.comicDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.characters.CharactersFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.eventDetails.EventFragment
import com.abaferastech.marvelapp.ui.events.EventsFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.seriesScreen.SeriesFragment

class ComicFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
    private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            CharactersFragment.newInstance(id,TYPE.CHARACTER)
        else if(position == 1){
            SeriesFragment.newInstance(id,TYPE.SERIES)
        }
        else {
            EventsFragment.newInstance(id,TYPE.EVENT)
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

}