package com.abaferastech.marvelapp.ui.comic.comicDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailsFragment :
    BaseFragment<FragmentComicDetailsBinding, ComicDetailsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_comic_details
    override val viewModelClass = ComicDetailsViewModel::class.java
    private val passedId: ComicDetailsFragmentArgs by navArgs()


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.hide()
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewModel.saveComicId(passedId.comicID)
    }

    private fun init() {
        setupPageAdapter()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Character"
                1 -> tab.text = "Creators"
                2 -> tab.text = "Details"
            }
        }.attach()
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun setupPageAdapter() {
        val adapter = ComicFragmentPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            passedId.comicID
        )
        binding.viewPager.adapter = adapter
    }
}
