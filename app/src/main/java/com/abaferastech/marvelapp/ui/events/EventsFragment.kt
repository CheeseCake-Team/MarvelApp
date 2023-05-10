package com.abaferastech.marvelapp.ui.events

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.databinding.FragmentEventsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utils.Constants.PUT_TYPE
import com.abaferastech.marvelapp.utils.Constants.TYPE_ID


class EventsFragment :
    BaseFragment<FragmentEventsBinding, EventsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_events

    override val viewModelClass: Class<EventsViewModel>
        get() = EventsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        val typeID = arguments?.getInt(TYPE_ID)!!
        when (arguments?.getParcelable<TYPE>(PUT_TYPE)) {
            TYPE.COMIC -> viewModel.getComicEvents(typeID)
            TYPE.SERIES ->  viewModel.getSeriesEvents(typeID)
            TYPE.CHARACTER -> viewModel.getCharacterEvents(typeID)
            else -> viewModel.getMarvelEvents()
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

        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = EventsFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE_ID, id)
                putParcelable(PUT_TYPE, type)
            }
        }
    }

}