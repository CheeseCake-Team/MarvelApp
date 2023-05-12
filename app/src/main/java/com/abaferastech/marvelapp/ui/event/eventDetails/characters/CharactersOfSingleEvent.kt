package com.abaferastech.marvelapp.ui.eventDetails.characters

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.databinding.CharactersOfSingleEventBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.characters.CharactersFragment
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.characters.CharactersViewModel
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.seriesScreen.SeriesFragment
import com.abaferastech.marvelapp.utils.Constants


class CharactersOfSingleEvent :
    BaseFragment<CharactersOfSingleEventBinding, CharactersViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.characters_of_single_event

    override val viewModelClass: Class<CharactersViewModel>
        get() = CharactersViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val typeID = arguments?.getInt(Constants.TYPE_ID)
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.EVENT -> viewModel.getEventCharacter(typeID!!)
            else -> viewModel.getAllCharacters()
        }

        val adapter = CharactersOfSingleEventAdapter(emptyList(), object :
            CharactersInteractionListener{
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