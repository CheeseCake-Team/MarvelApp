package com.abaferastech.marvelapp.ui.comic.comicsGrid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicsViewAllBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicEvents
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewModel
import com.abaferastech.marvelapp.ui.model.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ComicsGridFragment : BaseFragment<FragmentComicsViewAllBinding>() {

    override val layoutIdFragment = R.layout.fragment_comics_view_all

    override val viewModel: ComicsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel.getMarvelComics()
        setupComicsAdapter()
        addComicEvents()
    }

    private fun addComicEvents() {

        val clickComicEventObserver = EventObserver<ComicEvents> { event ->
            handleComicEvent(event)
        }

        viewModel.navigationEvents.observe(viewLifecycleOwner, clickComicEventObserver)
    }

    private fun handleComicEvent(event: ComicEvents?) {
        val action = when (event) {
            is ComicEvents.ClickComicEvent ->
                ComicsGridFragmentDirections
                    .actionComicsGridFragmentToComicDetailsFragment(event.comicID)

            null -> null
        }
        action?.let { it1 -> findNavController().navigate(it1) }
    }

    private fun setupComicsAdapter() {
        val adapter = ComicsGridAdapter(viewModel)
        binding.recyclerViewComics.adapter = adapter
    }
}