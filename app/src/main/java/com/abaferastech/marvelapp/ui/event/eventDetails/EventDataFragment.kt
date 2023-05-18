package com.abaferastech.marvelapp.ui.event.eventDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentEventDataBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class EventDataFragment : BaseFragment<FragmentEventDataBinding>() {
    override val layoutIdFragment = R.layout.fragment_event_data
    override val viewModel: EventViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeID = arguments?.getInt(Constants.TYPE_ID)
        viewModel.getSingleEvent(typeID!!)
    }


    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = EventDataFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}