package com.abaferastech.marvelapp.ui.eventScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.databinding.FragmentEventsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import java.util.Objects


class EventsFragment : BaseFragment<FragmentEventsBinding, EventsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_events

    override val viewModelClass: Class<EventsViewModel>
        get() = EventsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Ekko","here")
        val adapter = EventAdapter(emptyList(), object : EventsInteractionListener{})
        binding.recyclerViewEvents.adapter = adapter
    }
}