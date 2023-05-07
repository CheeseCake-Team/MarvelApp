package com.abaferastech.marvelapp.ui.eventDetailsScreen

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentEventBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment


class EventFragment : BaseFragment<FragmentEventBinding,EventviewMoel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_event

    override val viewModelClass: Class<EventviewMoel>
        get() = EventviewMoel::class.java



}