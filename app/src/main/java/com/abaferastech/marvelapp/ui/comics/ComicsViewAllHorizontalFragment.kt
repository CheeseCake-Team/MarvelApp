package com.abaferastech.marvelapp.ui.comics

import android.os.Bundle
import android.util.Log
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicsViewAllHorizontalBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.eventScreen.EventsFragment

class ComicsViewAllHorizontalFragment :
    BaseFragment<FragmentComicsViewAllHorizontalBinding, ComicsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_comics_view_all_horizontal

    override val viewModelClass: Class<ComicsViewModel>
        get() = ComicsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = arguments?.getInt(CHARACTER_ID)

        if (characterId != null) {
            viewModel.getCharacterComics(characterId)
        } else {
            viewModel.getMarvelComics()
        }

        val adapter = ComicsAdapter(emptyList(), object : ComicsInteractionListener {})
        binding.recyclerViewComics.adapter = adapter
    }

    companion object {
        private const val CHARACTER_ID = "character_id"
        @JvmStatic
        fun newInstance(id: Int) = ComicsViewAllHorizontalFragment().apply {
            arguments = Bundle().apply {
                putInt(CHARACTER_ID, id)
            }
        }
    }
}