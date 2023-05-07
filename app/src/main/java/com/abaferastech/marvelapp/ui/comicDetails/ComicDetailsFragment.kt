package com.abaferastech.marvelapp.ui.comicDetails

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.DataItem
import com.abaferastech.marvelapp.databinding.FragmentComicDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.character.CharacterFragment
import com.abaferastech.marvelapp.ui.comics.ComicsViewModel
import com.abaferastech.marvelapp.ui.home.NavigationInteractionListener

class ComicDetailsFragment :
    BaseFragment<FragmentComicDetailsBinding, ComicsViewModel>(),NavigationInteractionListener {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_comic_details
    override val viewModelClass: Class<ComicsViewModel>
        get() = ComicsViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterFragment = CharacterFragment()
        val fragment = DataItem.TabItem(characterFragment,1)
        viewModel.comics.observe(viewLifecycleOwner) {
            val comic = DataItem.HeaderDetailsItem(viewModel.comics.value!!.shuffled()[0],0)
            val adapter = ComicDetailsAdapter(listOf(comic,fragment), this,this )
            binding.recyclerViewComicsDetails.adapter = adapter
        }

    }

    override fun onNavigate(dataItem: DataItem) {
        TODO("Not yet implemented")
    }

}
