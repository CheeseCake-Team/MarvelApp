package com.abaferastech.marvelapp.ui.comics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.databinding.FragmentComicsBinding

import com.abaferastech.marvelapp.ui.base.BaseFragment
import io.reactivex.rxjava3.core.Observer


class ComicsFragment() :  BaseFragment<FragmentComicsBinding, ComicsViewModel>() ,ComicsInteractionListener{


    override val layoutIdFragment: Int
        get() = R.layout.fragment_comics
    override val viewModelClass: Class<ComicsViewModel>
        get() = ComicsViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter=ComicsAdapter(emptyList(),viewModel)
        binding.comicsList.adapter=adapter

        viewModel.comics.observe(this.viewLifecycleOwner,{
            Log.v("observe-fun", "${it}")
            var listOfComics=it
            adapter.setItems(listOfComics)
        })
    }

    override fun onClickComic(comics: Comics) {
        TODO("Not yet implemented")
    }

}