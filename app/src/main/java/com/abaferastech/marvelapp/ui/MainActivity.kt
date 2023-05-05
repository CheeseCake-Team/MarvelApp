package com.abaferastech.marvelapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Stories
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
        viewModel.stories.observe(this) {
            val adapter = StoriesAdapter(it, object:ComicsInteractionListener{
                override fun onClickSeries(comics: Comics) {
                    Log.i("story", comics.description.toString())
                }

            })
            binding.recyclerViewStories.adapter = adapter

        }
    }
}