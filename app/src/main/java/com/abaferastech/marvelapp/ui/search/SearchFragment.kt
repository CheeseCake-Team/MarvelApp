package com.abaferastech.marvelapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentSearchBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicsAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_search
    override val viewModelClass: Class<SearchViewModel>
        get() = SearchViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterButton.setOnClickListener {
            if(binding.horizontalScrollView.visibility == View.GONE)
                binding.horizontalScrollView.visibility = View.VISIBLE
            else{
                binding.horizontalScrollView.visibility = View.GONE
            }
        }

        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        viewModel.searchQuery.observe(viewLifecycleOwner) {
            viewModel.search(it)
        }
        val adapter = ComicsAdapter(emptyList(), object : ComicsInteractionListener {})
        binding.recyclerViewSearch.adapter = adapter
    }


}