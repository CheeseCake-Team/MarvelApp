package com.abaferastech.marvelapp.ui.characterDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCharacterBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterBinding, CharacterDetailsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_character
    override val viewModelClass: Class<CharacterDetailsViewModel>
        get() = CharacterDetailsViewModel::class.java

    private val args: CharacterDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSingleCharacter(args.chatacterId)

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
            args.chatacterId
        )
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "All comics"
                1 -> tab.text = "Events"
            }
        }.attach()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}