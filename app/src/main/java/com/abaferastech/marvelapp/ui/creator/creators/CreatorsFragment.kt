package com.abaferastech.marvelapp.ui.creator.creators

import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCreatorsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comicDetails.ComicDetailsFragmentDirections
import com.abaferastech.marvelapp.ui.event.eventDetails.EventFragmentDirections
import com.abaferastech.marvelapp.ui.model.EventObserver
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.series.seriesDetails.SeriesDetailsFragmentDirections
import com.abaferastech.marvelapp.utils.Constants

class CreatorsFragment : BaseFragment<FragmentCreatorsBinding, CreatorsViewModel>() {
    override val layoutIdFragment = R.layout.fragment_creators
    override val viewModelClass = CreatorsViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setAdapter()
        addComicEvents()
    }

    private fun setAdapter() {
        val adapter = CreatorsAdapter( viewModel)
        binding.recyclerViewCreators.adapter = adapter
    }

    private fun init() {
        val typeID = arguments?.getInt(Constants.TYPE_ID)
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.SERIES -> {
                viewModel.getSeriesCreators(typeID!!)
            }
            TYPE.COMIC -> {
                viewModel.getComicCreators(typeID!!)
            }
            else -> viewModel.getMarvelCreators()
        }
    }

    private fun addComicEvents() {
        val clickCreatorEventObserver = EventObserver<CreatorEvent> { event ->
            handleCreatorEvent(event)
        }
        viewModel.navigationEvents.observe(viewLifecycleOwner, clickCreatorEventObserver)
    }

    private fun handleCreatorEvent(event: CreatorEvent?) {
        val action = when (event) {
            is CreatorEvent.ClickCreatorEvent ->
                navDirections(event)
            null -> null
        }
        action?.let { it1 -> findNavController().navigate(it1) }
    }

    private fun navDirections(event: CreatorEvent.ClickCreatorEvent): NavDirections? {

        return when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.SERIES -> SeriesDetailsFragmentDirections
                .actionSeriesDetailsFragmentToCreatorDetailsFragment(event.creatorID)
            TYPE.COMIC -> ComicDetailsFragmentDirections
                .actionComicDetailsFragmentToCreatorDetailsFragment(event.creatorID)
            TYPE.EVENT -> EventFragmentDirections
                .actionEventFragmentToCreatorDetailsFragment(event.creatorID)
            else -> null
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = CreatorsFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}