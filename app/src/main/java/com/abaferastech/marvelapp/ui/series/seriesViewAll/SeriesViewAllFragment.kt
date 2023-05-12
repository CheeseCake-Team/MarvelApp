package com.abaferastech.marvelapp.ui.series.seriesViewAll

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesviewallBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicEvents
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewAllHorizontalFragmentDirections
import com.abaferastech.marvelapp.ui.series.seriesScreen.SeriesAdapter
import com.abaferastech.marvelapp.ui.series.seriesScreen.SeriesInteractionListener

class SeriesViewAllFragment : BaseFragment<FragmentSeriesviewallBinding, SeriesViewAllViewModel>() {
    override val layoutIdFragment = R.layout.fragment_seriesviewall
    override val viewModelClass = SeriesViewAllViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addAdapter()
        addComicEvents()
    }

    private fun addAdapter() {
        val adapter = SeriesViewAllAdapter(emptyList(), viewModel)
        binding.recyclerViewSeriesViewAll.adapter = adapter
    }


    private fun addComicEvents() {
        viewModel.navigationEvents.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled().let { event ->
                val action = when (event) {
                    is SeriesEvents.ClickSeriesEvent ->
                        SeriesViewAllFragmentDirections
                            .actionSeriesViewAllFragmentToSeriesDetailsFragment(event.seriesID)
                    null -> null
                }
                action?.let { it1 -> findNavController().navigate(it1) }

            }
        }
    }

}
