package com.abaferastech.marvelapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abaferastech.marvelapp.BR

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel> : Fragment() {

    abstract val layoutIdFragment: Int
    abstract val viewModelClass: Class<VM>

    val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    private lateinit var _binding: VDB

    protected val binding: VDB
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<VDB>(inflater, layoutIdFragment, container, false)

        _binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel,viewModel)
            return root
        }
    }

}