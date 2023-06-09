package com.abaferastech.marvelapp.ui.comic.comics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicsViewAllHorizontalBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.character.characterDetails.CharacterDetailsFragmentDirections
import com.abaferastech.marvelapp.ui.creator.creatorsDetails.CreatorDetailsFragmentDirections
import com.abaferastech.marvelapp.ui.event.eventDetails.EventFragmentDirections
import com.abaferastech.marvelapp.ui.model.EventObserver
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.series.seriesDetails.SeriesDetailsFragmentDirections
import com.abaferastech.marvelapp.util.Constants
import com.abaferastech.marvelapp.util.Constants.TYPE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ComicsViewAllHorizontalFragment :
    BaseFragment<FragmentComicsViewAllHorizontalBinding>() {

    override val layoutIdFragment = R.layout.fragment_comics_view_all_horizontal

    override val viewModel:ComicsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setupComicAdapter()
        addComicEvents()
    }

    private fun addComicEvents() {

        val clickComicEventObserver = EventObserver<ComicEvents> { event ->
            handleComicEvent(event)
        }

        viewModel.navigationEvents.observe(viewLifecycleOwner,clickComicEventObserver)
    }

    private fun handleComicEvent(event: ComicEvents?) {
        val action = when (event) {
            is ComicEvents.ClickComicEvent -> navDirections(event)
            null -> null
        }
        action?.let { it1 -> findNavController().navigate(it1) }
    }

    private fun init() {
        val typeID = arguments?.getInt(TYPE_ID)
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.SERIES -> viewModel.getSeriesComics(typeID!!)
            TYPE.CHARACTER -> viewModel.getCharacterComics(typeID!!)
            TYPE.EVENT -> viewModel.getEventComics(typeID!!)
            TYPE.CREATOR -> viewModel.getCreatorComics(typeID!!)
            else -> viewModel.getMarvelComics()
        }
    }

    private fun setupComicAdapter() {
        val adapter = ComicsAdapter(viewModel)
        binding.recyclerViewComics.adapter = adapter
    }

    private fun navDirections(event: ComicEvents.ClickComicEvent): NavDirections? {

        return when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.SERIES -> SeriesDetailsFragmentDirections
                .actionSeriesDetailsFragmentToComicDetailsFragment(event.comicID)
            TYPE.CHARACTER -> CharacterDetailsFragmentDirections
                .actionCharacterFragmentToComicDetailsFragment(event.comicID)
            TYPE.EVENT -> EventFragmentDirections
                .actionEventFragmentToComicDetailsFragment(event.comicID)
            TYPE.CREATOR -> CreatorDetailsFragmentDirections
                .actionCreatorDetailsFragmentToComicDetailsFragment(event.comicID)
            else -> null
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = ComicsViewAllHorizontalFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}