package com.abaferastech.marvelapp.ui.events

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.appcompat.app.AppCompatActivity
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.databinding.FragmentEventsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment


class EventsFragment :
    BaseFragment<FragmentEventsBinding, EventsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_events

    override val viewModelClass: Class<EventsViewModel>
        get() = EventsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        val characterId = arguments?.getInt(CHARACTER_ID)

        if (characterId != null) {
            viewModel.getCharacterEvents(characterId)
        } else {
            viewModel.getMarvelEvents()
        }

        val adapter = EventAdapter(emptyList(), object : EventsInteractionListener {
            override fun onEventClick(event: Events) {
                event.id.let {
                    val action =
                        EventsFragmentDirections.actionEventsFragmentToEventFragment(event.id!!)
                    findNavController().navigate(action)
                }
            }
        })
        binding.recyclerViewEvents.adapter = adapter
    }

    companion object {
        private const val CHARACTER_ID = "character_id"
        @JvmStatic
        fun newInstance(id: Int) = EventsFragment().apply {
            arguments = Bundle().apply {
                putInt(CHARACTER_ID, id)
            }
        }
    }

}