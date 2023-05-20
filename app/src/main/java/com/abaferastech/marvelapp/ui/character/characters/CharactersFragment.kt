package com.abaferastech.marvelapp.ui.character.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCharactersBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comicDetails.ComicDetailsFragmentDirections
import com.abaferastech.marvelapp.ui.event.eventDetails.EventFragmentDirections
import com.abaferastech.marvelapp.ui.home.adapters.CharactersAdapter
import com.abaferastech.marvelapp.ui.model.EventObserver
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.series.seriesDetails.SeriesDetailsFragmentDirections
import com.abaferastech.marvelapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    override val layoutIdFragment = R.layout.fragment_characters
    override val viewModel : CharactersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inti()
        setupCharactersAdapter()
        addCharactersEvent()
    }

    private fun inti() {
        val typeID = arguments?.getInt(Constants.TYPE_ID)
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.SERIES -> viewModel.getCharacterSeries(typeID!!)
            TYPE.COMIC -> viewModel.getCharacterComics(typeID!!)
            TYPE.EVENT -> viewModel.getEventCharacter(typeID!!)
            else -> viewModel.getAllCharacters()
        }
    }

    private fun addCharactersEvent() {

        val clickCharacterEventObserver = EventObserver<CharacterEvent> { event ->
            handleCharacterEvent(event)
        }

        viewModel.navigationEvents.observe(viewLifecycleOwner, clickCharacterEventObserver)

    }

    private fun handleCharacterEvent(event: CharacterEvent) {
        val action = when (event) {
            is CharacterEvent.ClickCharacterEvent ->
                navDirections(event)
        }
        action?.let { it1 -> findNavController().navigate(it1) }

    }

    private fun navDirections(event: CharacterEvent.ClickCharacterEvent): NavDirections? {

        return when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.SERIES -> SeriesDetailsFragmentDirections
                .actionSeriesDetailsFragmentToCharacterFragment(event.characterID)
            TYPE.COMIC -> ComicDetailsFragmentDirections
                .actionComicDetailsFragmentToCharacterFragment(event.characterID)
            TYPE.EVENT -> EventFragmentDirections
                .actionEventFragmentToCharacterFragment(event.characterID)
            else -> CharactersFragmentDirections
                .actionCharactersFragmentToCharacterFragment(event.characterID)
        }
    }

    private fun setupCharactersAdapter() {
        val adapter = CharactersAdapter(viewModel)
        binding.recyclerViewCharacters.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = CharactersFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}