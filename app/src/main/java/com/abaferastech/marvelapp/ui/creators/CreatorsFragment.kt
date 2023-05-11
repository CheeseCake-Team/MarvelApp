package com.abaferastech.marvelapp.ui.creators

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Creators
import com.abaferastech.marvelapp.databinding.FragmentCreatorsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.characters.ViewAllCharactersAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewAllHorizontalFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utils.Constants

class CreatorsFragment : BaseFragment<FragmentCreatorsBinding,CreatorsViewModel>(){
    override val layoutIdFragment: Int
        get() = R.layout.fragment_creators
    override val viewModelClass: Class<CreatorsViewModel>
        get() = CreatorsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeID = arguments?.getInt(Constants.TYPE_ID)
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.SERIES -> viewModel.getSeriesCreators(typeID!!)
            TYPE.COMIC -> viewModel.getComicCreators(typeID!!)
            else -> viewModel.getMarvelCreators()
        }

        val adapter = CreatorsAdapter(emptyList(), object : CreatorsInteractionListener {
            override fun onClickCreators(creators: Creators) {
                TODO("Not yet implemented")
            }
        })
        binding.recyclerViewCreators.adapter = adapter
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