package com.abaferastech.marvelapp.ui.comic.comics

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicsViewAllHorizontalBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utils.Constants

class ComicsViewAllHorizontalFragment :
    BaseFragment<FragmentComicsViewAllHorizontalBinding, ComicsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_comics_view_all_horizontal

    override val viewModelClass: Class<ComicsViewModel>
        get() = ComicsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type =  arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)
        when(type){
            TYPE.COMIC -> TODO()
            TYPE.SERIES -> TODO()
            TYPE.CHARACTER -> {
                val characterId = arguments?.getInt(Constants.CHARACTER_ID)!!
                viewModel.getCharacterComics(characterId)
            }
            TYPE.EVENT -> TODO()
            else -> viewModel.getMarvelComics()
        }

        val adapter = ComicsAdapter(emptyList(), object : ComicsInteractionListener {})
        binding.recyclerViewComics.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int,type:TYPE) = ComicsViewAllHorizontalFragment().apply {
            arguments = Bundle().apply {
                when (type) {
                    TYPE.COMIC -> TODO()
                    TYPE.SERIES -> TODO()
                    TYPE.CHARACTER -> {
                        putInt(Constants.CHARACTER_ID, id)
                        putParcelable(Constants.PUT_TYPE,type)
                    }
                    TYPE.EVENT -> TODO()
                }

            }
        }
    }
}