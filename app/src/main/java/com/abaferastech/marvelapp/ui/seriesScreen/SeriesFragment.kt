package com.abaferastech.marvelapp.ui.seriesScreen

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utils.Constants.PUT_TYPE
import com.abaferastech.marvelapp.utils.Constants.TYPE_ID

class SeriesFragment : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_series
    override val viewModelClass: Class<SeriesViewModel>
        get() = SeriesViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val typeID = arguments?.getInt(TYPE_ID)
        when (arguments?.getParcelable<TYPE>(PUT_TYPE)) {

            TYPE.COMIC -> viewModel.getComicSeries(typeID!!)

            else -> viewModel.getMarvelSeries()
        }
        val adapter = SeriesAdapter(emptyList(), object : SeriesInteractionListener {})
        binding.recyclerViewSeries.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = SeriesFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE_ID, id)
                putParcelable(PUT_TYPE, type)
            }
        }
    }

}
