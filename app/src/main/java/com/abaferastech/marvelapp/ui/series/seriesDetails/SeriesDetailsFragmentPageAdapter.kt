package com.abaferastech.marvelapp.ui.series.seriesDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.character.characters.CharactersFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.creator.creators.CreatorsFragment
import com.abaferastech.marvelapp.ui.model.TYPE

class SeriesDetailsFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
    private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> CharactersFragment.newInstance(id, TYPE.SERIES)
            1 -> CreatorsFragment.newInstance(id, TYPE.SERIES)
            else -> ComicsViewAllHorizontalFragment.newInstance(id, TYPE.SERIES)
        }

    }
}