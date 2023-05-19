package com.abaferastech.marvelapp.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentFavouritesBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_favourites
    override val viewModel: FavouritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        setupCharacterAdapter()
    }

    private fun setupCharacterAdapter() {
        val adapter = FavouritesAdapter(viewModel)
        binding.recyclerViewFavourits.adapter = adapter
    }
}