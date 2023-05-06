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

    fun init() {
        viewModel.events.observe(requireActivity()) {
            binding.recyclerViewEvents.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    // Check if the user has scrolled to the end of the list
                    if (!recyclerView.canScrollVertically(1)) {
                        // Request new data here
                        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                        val totalItemCount = layoutManager.itemCount

                        if (lastVisibleItemPosition >= totalItemCount - 1) {
                            // End of list reached, load more data
                            // Make API request to get more data and append it to the list
                            // Update the RecyclerView adapter
                            viewModel.getMarvelEvents()
                        }
                    }
                }
            })
        }
    }





}