package com.abaferastech.marvelapp.ui.comic.comicsGrid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentComicsViewAllBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.comic.comics.ComicsViewModel


class ComicsGridFragment : BaseFragment<FragmentComicsViewAllBinding, ComicsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_comics_view_all

    override val viewModelClass: Class<ComicsViewModel>
        get() = ComicsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        viewModel.getMarvelComics()
        val adapter = ComicsAdapter(emptyList(), object : ComicsInteractionListener {})
        binding.recyclerViewComics.adapter = adapter
    }



}