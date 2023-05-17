package com.abaferastech.marvelapp.ui.creator.creatorsDetails

import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCreatorDataBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.util.Constants


class CreatorDataFragment : BaseFragment<FragmentCreatorDataBinding, CreatorDetailsViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_creator_data
    override val viewModelClass: Class<CreatorDetailsViewModel>
        get() = CreatorDetailsViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeID = arguments?.getInt(Constants.TYPE_ID)
        viewModel.getMarvelCreator(typeID!!)
    }


    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = CreatorDataFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}
