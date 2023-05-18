package com.abaferastech.marvelapp.ui.creator.creatorsDetails

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCreatorDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class CreatorDetailsFragment() :
    BaseFragment<FragmentCreatorDetailsBinding>(), Parcelable {

    override val layoutIdFragment = R.layout.fragment_creator_details
    override val viewModel: CreatorDetailsViewModel by viewModels()

    constructor(parcel: Parcel) : this() {
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMarvelCreator()
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
            viewModel.creatorArgs.creatorId
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CreatorDetailsFragment> {
        override fun createFromParcel(parcel: Parcel): CreatorDetailsFragment {
            return CreatorDetailsFragment(parcel)
        }

        override fun newArray(size: Int): Array<CreatorDetailsFragment?> {
            return arrayOfNulls(size)
        }
    }

}