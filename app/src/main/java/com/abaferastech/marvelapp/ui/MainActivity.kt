package com.abaferastech.marvelapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.abaferastech.marvelapp.BuildConfig
import com.abaferastech.marvelapp.databinding.ActivityMainBinding
import com.abaferastech.marvelapp.milk.MarvelViewModel
import java.security.MessageDigest
import java.time.Instant


class MainActivity : AppCompatActivity() {
    lateinit var viewModel :MarvelViewModel
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[com.abaferastech.marvelapp.milk.MarvelViewModel::class.java]
        binding.text.text= viewModel.stories.toString()


    }



}