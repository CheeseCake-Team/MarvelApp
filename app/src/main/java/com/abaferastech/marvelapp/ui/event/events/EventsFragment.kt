package com.abaferastech.marvelapp.ui.event.events

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentEventsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.character.characterDetails.CharacterDetailsFragmentDirections
import com.abaferastech.marvelapp.ui.creator.creatorsDetails.CreatorDetailsFragmentDirections
import com.abaferastech.marvelapp.ui.model.EventObserver
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.util.Constants.PUT_TYPE
import com.abaferastech.marvelapp.util.Constants.TYPE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class EventsFragment :
    BaseFragment<FragmentEventsBinding>() {

    override val layoutIdFragment = R.layout.fragment_events

    override val viewModel: EventsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        init()
        setupAdapter()
        addComicEvents()
    }

    private fun setupAdapter() {
        val adapter = EventAdapter(viewModel)
        binding.recyclerViewEvents.adapter = adapter
    }

    private fun init() {
        val typeID = arguments?.getInt(TYPE_ID)
        when (arguments?.getParcelable<TYPE>(PUT_TYPE)) {
            TYPE.COMIC -> viewModel.getComicEvents(typeID!!)
            TYPE.CHARACTER -> viewModel.getCharacterEvents(typeID!!)
            TYPE.CREATOR -> viewModel.getCreatorEvents(typeID!!)
            else -> viewModel.getMarvelEvents()
        }
    }

    private fun addComicEvents() {
        val clickEventEventObserver = EventObserver<EvenEvents> { event ->
            handleEventEvent(event)
        }

        viewModel.navigationEvents.observe(viewLifecycleOwner, clickEventEventObserver)
    }

    private fun handleEventEvent(event: EvenEvents?) {
        val action = when (event) {
            is EvenEvents.ClickEventEvents -> navDirections(event)

            null -> null
        }
        action?.let { it1 -> findNavController().navigate(it1) }
    }

    private fun navDirections(event: EvenEvents.ClickEventEvents): NavDirections {

        return when (arguments?.getParcelable<TYPE>(PUT_TYPE)) {
            TYPE.CHARACTER -> CharacterDetailsFragmentDirections
                .actionCharacterFragmentToEventFragment(event.eventID)

            TYPE.CREATOR -> CreatorDetailsFragmentDirections
                .actionCreatorDetailsFragmentToEventFragment(event.eventID)

            else -> EventsFragmentDirections
                .actionEventsFragmentToEventFragment(event.eventID)
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

    override fun onResume() {
        super.onResume()
        if (viewModel.stateInitialized()) {
            binding.recyclerViewEvents.layoutManager?.onRestoreInstanceState(
                viewModel.restoreRecyclerViewState()
            )
        }
    }

    override fun onPause() {
        super.onPause()
        binding.recyclerViewEvents.layoutManager?.onSaveInstanceState()
            ?.let { viewModel.saveRecyclerViewState(it) }
    }


}