package com.abaferastech.marvelapp.ui.character

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCharacterDetailsBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment

class CharacterDetailsFragment : BaseFragment<FragmentCharacterDetailsBinding, CharacterViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_character_details
    override val viewModelClass: Class<CharacterViewModel>
        get() = CharacterViewModel::class.java


}