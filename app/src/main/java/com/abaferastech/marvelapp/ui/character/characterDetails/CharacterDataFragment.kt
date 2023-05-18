package com.abaferastech.marvelapp.ui.character.characterDetails


import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCharacterDataBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utilities.Constants


class CharacterDataFragment : BaseFragment<FragmentCharacterDataBinding, CharacterDetailsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_character_data
    override val viewModelClass: Class<CharacterDetailsViewModel>
        get() = CharacterDetailsViewModel::class.java


    companion object {
        @JvmStatic
        fun newInstance(id: Int, type: TYPE) = CharacterDataFragment().apply {
            arguments = Bundle().apply {
                putInt(Constants.TYPE_ID, id)
                putParcelable(Constants.PUT_TYPE, type)
            }
        }
    }
}