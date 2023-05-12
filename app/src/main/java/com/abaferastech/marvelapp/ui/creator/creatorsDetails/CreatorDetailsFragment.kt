package com.abaferastech.marvelapp.ui.creator.creatorsDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCreatorDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.character.characterDetails.CharacterFragmentPageAdapter
import com.google.android.material.tabs.TabLayoutMediator


class CreatorDetailsFragment :
    BaseFragment<FragmentCreatorDetailsBinding, CreatorDetailsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_creator_details
    override val viewModelClass: Class<CreatorDetailsViewModel>
        get() = CreatorDetailsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMarvelCreator(15)
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
        val adapter = CreatorFragmentPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            15
        )
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "All comics"
                1 -> tab.text = "Events"
                2 -> tab.text = "Details"
            }
        }.attach()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}