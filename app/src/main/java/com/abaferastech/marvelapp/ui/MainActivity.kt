package com.abaferastech.marvelapp.ui

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.abaferastech.marvelapp.databinding.ActivityMainBinding
import com.abaferastech.marvelapp.milk.MarvelViewModel
import com.abaferastech.marvelapp.ui.base.BaseActivity

class MainActivity() : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    lateinit var viewModel: MarvelViewModel


    override fun init() {
        viewModel = ViewModelProvider(this)[MarvelViewModel::class.java]
        viewModel.series.observe(this) {
            binding.text.text = it[0].toString()
        }
    }


}