package com.abaferastech.marvelapp.ui.comic.comicsGrid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicsViewAllBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicEvents
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewModel


class ComicsGridFragment : BaseFragment<FragmentComicsViewAllBinding, ComicsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_comics_view_all

    override val viewModelClass = ComicsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel.getMarvelComics()
        setupComicsAdapter()
        addComicEvents()
    }

    private fun addComicEvents() {
        viewModel.navigationEvents.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled().let { event ->
                val action = when (event) {
                    is ComicEvents.ClickComicEvent ->
                        ComicsGridFragmentDirections
                            .actionComicsGridFragmentToComicDetailsFragment(event.comicID)
                    null -> null
                }
                action?.let { it1 -> findNavController().navigate(it1) }

            }
        }
    }
    private fun setupComicsAdapter() {
        val adapter = ComicsGridAdapter(emptyList(), viewModel)
        binding.recyclerViewComics.adapter = adapter
    }
}