package com.abaferastech.marvelapp.ui.comic.comics

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicsViewAllHorizontalBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utils.Constants
import com.abaferastech.marvelapp.utils.Constants.TYPE_ID

class ComicsViewAllHorizontalFragment :
    BaseFragment<FragmentComicsViewAllHorizontalBinding, ComicsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_comics_view_all_horizontal

    override val viewModelClass: Class<ComicsViewModel>
        get() = ComicsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeID = arguments?.getInt(TYPE_ID)!!
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.SERIES -> viewModel.getSeriesComics(typeID)
            TYPE.CHARACTER -> viewModel.getCharacterComics(typeID)
            TYPE.EVENT -> viewModel.getEventComics(typeID)
            TYPE.CREATOR -> viewModel.getEventComics(typeID)
            else -> viewModel.getMarvelComics()
        }

        val adapter = ComicsAdapter(emptyList(), object : ComicsInteractionListener {})
        binding.recyclerViewComics.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = ComicsViewAllHorizontalFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}