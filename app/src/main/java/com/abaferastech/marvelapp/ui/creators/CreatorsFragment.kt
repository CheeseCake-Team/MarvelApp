package com.abaferastech.marvelapp.ui.creators

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCreatorBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment


class CreatorsFragment : BaseFragment<FragmentCreatorBinding,CreatorsViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override val layoutIdFragment: Int
        get() = R.layout.fragment_creator
    override val viewModelClass: Class<CreatorsViewModel>
        get() = CreatorsViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator, container, false)
    }

}