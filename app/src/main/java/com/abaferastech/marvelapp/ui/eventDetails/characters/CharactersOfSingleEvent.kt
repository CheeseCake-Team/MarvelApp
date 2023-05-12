package com.abaferastech.marvelapp.ui.eventDetailsScreen.characters

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.databinding.CharactersOfSingleEventBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.eventScreen.EventsViewModel


class CharactersOfSingleEvent :
    BaseFragment<CharactersOfSingleEventBinding, EventsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.characters_of_single_event

    override val viewModelClass: Class<EventsViewModel>
        get() = EventsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventId = arguments?.getInt(EVENT_ID)

        if (eventId != null) {
            viewModel.getEventCharacters(eventId)
        }

        val adapter = CharactersOfSingleEventAdapter(emptyList(), object :
        CharactersInteractionListener{
            override fun onClickCharacter(character: Characters) {

            }

        })
        binding.recyclerViewCharacters.adapter = adapter
    }

    companion object {
        private const val EVENT_ID = "event_id"
        @JvmStatic
        fun newInstance(id: Int) = CharactersOfSingleEvent().apply {
            arguments = Bundle().apply {
                putInt(EVENT_ID, id)
            }
        }
    }
}