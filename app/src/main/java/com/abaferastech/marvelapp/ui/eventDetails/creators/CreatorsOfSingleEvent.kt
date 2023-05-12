package com.abaferastech.marvelapp.ui.eventDetailsScreen.creators

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Creators
import com.abaferastech.marvelapp.databinding.CreatorsOfSingleEventBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.eventScreen.EventsViewModel


class CreatorsOfSingleEvent :
    BaseFragment<CreatorsOfSingleEventBinding, EventsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.creators_of_single_event

    override val viewModelClass: Class<EventsViewModel>
        get() = EventsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventId = arguments?.getInt(EVENT_ID)

        if (eventId != null) {
            viewModel.getEventCreators(eventId)
        }

        val adapter = CreatorsOfSingleEventAdapter(emptyList(), object :
            CreatorsOfSingleEventAdapter.CreatorsInteractionListener {
        })
        binding.recyclerViewCreators.adapter = adapter
    }

    companion object {
        private const val EVENT_ID = "event_id"
        @JvmStatic
        fun newInstance(id: Int) = CreatorsOfSingleEvent().apply {
            arguments = Bundle().apply {
                putInt(EVENT_ID, id)
            }
        }
    }
}