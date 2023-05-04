package com.abaferastech.marvelapp.ui

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Stories
import com.abaferastech.marvelapp.databinding.ActivityMainBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment

class StoriesFragment : BaseFragment<ActivityMainBinding, MarvelViewModel>(),
   StoriesInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override val layoutIdFragment: Int
        get() = R.layout.activity_main
    override val viewModelClass: Class<MarvelViewModel>
        get() = MarvelViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        val storiesAdapter = StoriesAdapter(emptyList(), this)

        binding.recyclerViewStories.adapter = storiesAdapter
    }

    override fun onClickSeries(stories: Stories) {
        TODO("Not yet implemented")
    }
}