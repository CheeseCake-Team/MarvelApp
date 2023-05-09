package com.abaferastech.marvelapp.ui.eventDetailsScreen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentEventBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicsAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener


class EventFragment : BaseFragment<FragmentEventBinding,EventViewModel>() {

    private val args: EventFragmentArgs by navArgs()

    override val layoutIdFragment: Int
        get() = R.layout.fragment_event

    override val viewModelClass: Class<EventViewModel>
        get() = EventViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSingleEvent(args.eventId)
        viewModel.getEventComics(args.eventId)
        viewModel.comics.observe(viewLifecycleOwner, Observer {
            val adapter = ComicsAdapter(it, object : ComicsInteractionListener {})
            binding.recyclerViewEventComics.adapter = adapter
        })
    }


}