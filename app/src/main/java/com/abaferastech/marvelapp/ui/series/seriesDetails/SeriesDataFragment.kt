package com.abaferastech.marvelapp.ui.series.seriesDetails

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSeriesDataBinding

import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utilities.Constants

class SeriesDataFragment : BaseFragment<FragmentSeriesDataBinding, SeriesDetailsViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_series_data
    override val viewModelClass: Class<SeriesDetailsViewModel>
        get() = SeriesDetailsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val typeID = arguments?.getInt(Constants.TYPE_ID)
        viewModel.getSeriesById(typeID!!)*/
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = SeriesDataFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}