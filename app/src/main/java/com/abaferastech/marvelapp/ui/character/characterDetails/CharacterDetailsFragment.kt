package com.abaferastech.marvelapp.ui.character.characterDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCharacterBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.home.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterBinding>() {

    override val layoutIdFragment = R.layout.fragment_character
    override val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allCharacters.observe(viewLifecycleOwner) { isFavourite ->
//            if (isFavourite) {
//                binding.buttonFavourites.isChecked = true
//            }
            Log.i( "onViewCreated: ","${isFavourite.toString()}")
        }

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
                    viewModel.insertCharacter()
                    Toast.makeText(requireContext(), "added to room", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.deleteCharacter()
                    Toast.makeText(requireContext(), "removed from room", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getSingleCharacter()
        init()
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

    private fun init() {
        val adapter = CharacterFragmentPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            viewModel.characterArgs.characterId
        )
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Comics"
                1 -> tab.text = "Events"
                2 -> tab.text = "Details"
            }
        }.attach()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}