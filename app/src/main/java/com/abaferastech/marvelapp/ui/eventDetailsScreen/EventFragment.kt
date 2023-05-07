package com.abaferastech.marvelapp.ui.eventDetailsScreen

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentEventBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment


class EventFragment : BaseFragment<FragmentEventBinding,EventViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_event

    override val viewModelClass: Class<EventViewModel>
        get() = EventViewModel::class.java



}