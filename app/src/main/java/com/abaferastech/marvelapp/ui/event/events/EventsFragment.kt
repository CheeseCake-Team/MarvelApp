package com.abaferastech.marvelapp.ui.event.events

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.databinding.FragmentEventsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.character.characterDetails.CharacterDetailsFragmentDirections
import com.abaferastech.marvelapp.ui.comic.comics.ComicEvents
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utils.Constants.PUT_TYPE
import com.abaferastech.marvelapp.utils.Constants.TYPE_ID


class EventsFragment :
    BaseFragment<FragmentEventsBinding, EventsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_events

    override val viewModelClass = EventsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        init()
        setupAdapter()
        addComicEvents()
    }

    private fun setupAdapter() {
        val adapter = EventAdapter(emptyList(), viewModel)
        binding.recyclerViewEvents.adapter = adapter
    }

    private fun init() {
        val typeID = arguments?.getInt(TYPE_ID)
        when (arguments?.getParcelable<TYPE>(PUT_TYPE)) {
            TYPE.COMIC -> viewModel.getComicEvents(typeID!!)
            TYPE.CHARACTER -> viewModel.getCharacterEvents(typeID!!)
            else -> viewModel.getMarvelEvents()
        }
    }

    private fun addComicEvents() {
        viewModel.navigationEvents.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled().let { event ->
                val action = when (event) {
                    is EvenEvents.ClickEventEvents ->
                        EventsFragmentDirections
                            .actionEventsFragmentToEventFragment(event.eventID)
                    null -> null
                }
                action?.let { it1 -> findNavController().navigate(it1) }

            }
        }
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