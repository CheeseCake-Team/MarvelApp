package com.abaferastech.marvelapp.ui.event.eventDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentEventBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class EventFragment : BaseFragment<FragmentEventBinding>() {

    override val layoutIdFragment = R.layout.fragment_event

    override val viewModel: EventViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSingleEvent()
        init()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.hide()
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.show()
        }
    }

    private fun init() {
        val adapter = EventFragmentPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            viewModel.eventArgs.eventId
        )
        binding.eventViewPager.adapter = adapter
        TabLayoutMediator(binding.eventTabLayout, binding.eventViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Characters"
                1 -> tab.text = "Creators"
                2 -> tab.text = "Comics"
                3 -> tab.text = "Details"
            }
        }.attach()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}