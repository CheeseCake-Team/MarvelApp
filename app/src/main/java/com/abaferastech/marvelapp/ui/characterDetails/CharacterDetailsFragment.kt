package com.abaferastech.marvelapp.ui.characterDetails

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCharacterBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment

class CharacterDetailsFragment : BaseFragment<FragmentCharacterBinding, CharacterViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.item_header_details
    override val viewModelClass: Class<CharacterViewModel>
        get() = CharacterViewModel::class.java


}