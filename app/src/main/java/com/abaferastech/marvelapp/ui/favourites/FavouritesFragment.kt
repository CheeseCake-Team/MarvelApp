package com.abaferastech.marvelapp.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentFavouritesBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.favourites.favouritesAdapters.FavouriteCharactersAdapter
import com.abaferastech.marvelapp.ui.favourites.favouritesAdapters.FavouriteComicsAdapter
import com.abaferastech.marvelapp.ui.favourites.favouritesAdapters.FavouriteSeriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_favourites
    override val viewModel: FavouritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)


        viewModel.favouritesType.observe(viewLifecycleOwner) { type ->
            when (type) {
                FavouritesType.COMICS -> {
                    viewModel.getAllCachedComics()
                    val adapter = FavouriteComicsAdapter(viewModel)
                    binding.recyclerViewFavourites.adapter = adapter
                }
                FavouritesType.CHARACTERS -> {
                    viewModel.getCachedCharacters()
                    val adapter = FavouriteCharactersAdapter(viewModel)
                    binding.recyclerViewFavourites.adapter = adapter

                }
                FavouritesType.SERIES -> {
                    viewModel.getAllCachedSeries()
                    val adapter = FavouriteSeriesAdapter(viewModel)
                    binding.recyclerViewFavourites.adapter = adapter

                }
                FavouritesType.EVENTS -> {

                }
            }
        }
    }


}

