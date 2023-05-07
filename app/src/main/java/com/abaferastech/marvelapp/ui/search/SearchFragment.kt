package com.abaferastech.marvelapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSearchBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comics.ComicsAdapter
import com.abaferastech.marvelapp.ui.comics.ComicsInteractionListener

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_search
    override val viewModelClass: Class<SearchViewModel>
        get() = SearchViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchQuery.observe(viewLifecycleOwner, Observer{
            viewModel.search(it)
        })
        viewModel.searchResult.observe(viewLifecycleOwner, Observer{
            val adapter = ComicsAdapter(it,object: ComicsInteractionListener {

            })
            binding.recyclerViewSearch.adapter = adapter
        })
    }
}