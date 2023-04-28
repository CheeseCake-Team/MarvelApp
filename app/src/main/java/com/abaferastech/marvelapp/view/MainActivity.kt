package com.abaferastech.marvelapp.view

import android.view.LayoutInflater
import com.abaferastech.marvelapp.databinding.ActivityMainBinding
import com.abaferastech.marvelapp.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun init() {
        //TODO("Not yet implemented")
    }
}