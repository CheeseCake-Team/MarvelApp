package com.abaferastech.marvelapp.ui.comic.comics

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicsViewAllHorizontalBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.character.characters.CharactersFragmentDirections
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

        init()
        setupComicAdapter()
        addComicEvents()
    }

    private fun addComicEvents() {
        viewModel.navigationEvents.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled().let { event ->
                val action = when (event) {
                    is ComicEvents.ClickComicEvent ->
                        CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(
                            event.comicID
                        )
                    null -> null
                }
                action?.let { it1 -> findNavController().navigate(it1) }
            }
        }
    }

    private fun init() {
        val typeID = arguments?.getInt(TYPE_ID)
        when (arguments?.getParcelable<TYPE>(Constants.PUT_TYPE)) {
            TYPE.SERIES -> viewModel.getSeriesComics(typeID!!)
            TYPE.CHARACTER -> viewModel.getCharacterComics(typeID!!)
            TYPE.EVENT -> viewModel.getEventComics(typeID!!)
            TYPE.CREATOR -> viewModel.getEventComics(typeID!!)
            else -> viewModel.getMarvelComics()
        }
    }

    private fun setupComicAdapter() {
        val adapter = ComicsAdapter(emptyList(), viewModel)
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