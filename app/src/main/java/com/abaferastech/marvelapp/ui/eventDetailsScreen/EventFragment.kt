package com.abaferastech.marvelapp.ui.eventDetailsScreen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentEventBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comics.ComicsAdapter


class EventFragment : BaseFragment<FragmentEventBinding,EventViewModel>() {

    private val args: EventFragmentArgs by navArgs()
    private val eventId = args.eventId

    override val layoutIdFragment: Int
        get() = R.layout.fragment_event

    override val viewModelClass: Class<EventViewModel>
        get() = EventViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEventComics(eventId)
        viewModel.comics.observe(viewLifecycleOwner, Observer {
//            val adapter = ComicsAdapter(it, object )
        })
    }


}