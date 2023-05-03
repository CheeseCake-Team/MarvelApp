package com.abaferastech.marvelapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[MarvelViewModel::class.java]
        viewModel.series.observe(this) {
            binding.text.text = it.toString()
        }
    }


}