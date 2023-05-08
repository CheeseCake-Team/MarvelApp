package com.abaferastech.marvelapp.ui.eventScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.databinding.FragmentEventsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import java.util.Objects


class EventsFragment(private val id: Int? = null) :
    BaseFragment<FragmentEventsBinding, EventsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_events

    override val viewModelClass: Class<EventsViewModel>
        get() = EventsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterId = arguments?.getInt(CHARACTER_ID)

        if (characterId != null) {
            Log.d("TAG", "onViewCreated: there is id $characterId")
            viewModel.getEventsById(characterId)
        } else {
            viewModel.getMarvelEvents()
        }

        val adapter = EventAdapter(emptyList(), object : EventsInteractionListener {
            override fun onEventClick(event: Events) {
                event.id.let {
                    Log.i("MUjtaba", "123")
                    val action =
                        EventsFragmentDirections.actionEventsFragmentToEventFragment2(event.id!!)
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