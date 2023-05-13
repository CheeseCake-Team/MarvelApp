package com.abaferastech.marvelapp.ui.comic.comicDetails

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicDataBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utils.Constants


class ComicDataFragment : BaseFragment<FragmentComicDataBinding, ComicDetailsViewModel>(){
    override val layoutIdFragment: Int
        get() = R.layout.fragment_comic_data
    override val viewModelClass: Class<ComicDetailsViewModel>
        get() = ComicDetailsViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeID = arguments?.getInt(Constants.TYPE_ID)
        viewModel.getSingleComic(typeID!!
        )
    }




    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = ComicDataFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}