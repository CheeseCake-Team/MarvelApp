package com.abaferastech.marvelapp.ui.comic.comicDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abaferastech.marvelapp.ui.characters.CharactersFragment
import com.abaferastech.marvelapp.ui.creators.CreatorsFragment
import com.abaferastech.marvelapp.ui.model.TYPE

class ComicFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
    private val id: Int
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            CharactersFragment.newInstance(id,TYPE.COMIC)
        else {
            CreatorsFragment.newInstance(id,TYPE.COMIC)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

}