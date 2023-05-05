package com.abaferastech.marvelapp.ui.characters

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCharactersBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.eventScreen.EventAdapter
import com.abaferastech.marvelapp.ui.eventScreen.EventsInteractionListener

class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_characters
    override val viewModelClass: Class<CharactersViewModel>
        get() = CharactersViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CharactersAdapter(emptyList(), object : CharactersInteractionListener {})
        binding.recyclerViewCharacters.adapter = adapter
    }
}