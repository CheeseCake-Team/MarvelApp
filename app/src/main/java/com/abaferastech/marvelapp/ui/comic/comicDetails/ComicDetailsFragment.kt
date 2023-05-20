package com.abaferastech.marvelapp.ui.comic.comicDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicDetailsFragment :
    BaseFragment<FragmentComicDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_comic_details

    override val viewModel: ComicDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFavourites.setOnClickListener {
            if ((it as CheckBox).isChecked) {
                viewModel.isFavouriteClicked.postValue(true)
            } else {
                viewModel.isFavouriteClicked.postValue(false)
            }
        }

        viewModel.isFavouriteClicked.observe(viewLifecycleOwner) { isClicked ->
            isClicked?.let {
                if (it) {
                    viewModel.insertComic()
                    Toast.makeText(requireContext(), "added to room", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.deleteComic()
                    Toast.makeText(requireContext(), "removed from room", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.isComicFavourite.observe(viewLifecycleOwner){
            it.let {
                binding.buttonFavourites.isChecked = it
            }
        }

        viewModel.getSingleComic()
        init()
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
            viewModel.comicArgs.comicID
        )
        binding.viewPager.adapter = adapter
    }

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

}
