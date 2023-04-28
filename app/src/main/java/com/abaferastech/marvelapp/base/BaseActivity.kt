package com.abaferastech.mylearner.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract val bindingInflater: (LayoutInflater) -> VB

    private var _binding: VB? = null

    protected val binding: VB
        get() = _binding as VB



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(_binding!!.root)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun init()

}