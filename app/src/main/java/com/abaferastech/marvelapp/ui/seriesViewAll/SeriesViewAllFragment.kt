package com.abaferastech.marvelapp.ui.seriesViewAll

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesviewallBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.seriesScreen.SeriesAdapter
import com.abaferastech.marvelapp.ui.seriesScreen.SeriesInteractionListener

class SeriesViewAllFragment : BaseFragment<FragmentSeriesviewallBinding, SeriesViewAllViewModel>() {
    override val layoutIdFragment: Int
    get() = R.layout.fragment_seriesviewall
    override val viewModelClass: Class<SeriesViewAllViewModel>
    get() = SeriesViewAllViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SeriesViewAllAdapter(emptyList(), object : SeriesViewAllInteractionListener {})
        binding.recyclerViewSeriesViewAll.adapter = adapter
    }

}
