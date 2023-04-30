package com.abaferastech.marvelapp.ui.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder(binding: ViewDataBinding):RecyclerView.ViewHolder(binding.root)