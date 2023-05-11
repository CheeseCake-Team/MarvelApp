package com.abaferastech.marvelapp.ui.characters

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.databinding.FragmentCharactersBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.events.EventsFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utils.Constants

class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_characters
    override val viewModelClass: Class<CharactersViewModel>
        get() = CharactersViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeID = arguments?.getInt(Constants.TYPE_ID)
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.COMIC -> viewModel.getCharacterComics(typeID!!)
            TYPE.SERIES ->  viewModel.getCharacterSeries(typeID!!)
            TYPE.CHARACTER -> viewModel.getEventCharacter(typeID!!)
            else -> {}
        }

        val adapter = ViewAllCharactersAdapter(emptyList(), object : CharactersInteractionListener {
            override fun onClickCharacter(character: Characters) {

            }
        })
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