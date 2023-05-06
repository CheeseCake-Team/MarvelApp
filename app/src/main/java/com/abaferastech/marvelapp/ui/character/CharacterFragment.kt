package com.abaferastech.marvelapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCharacterBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment

class CharacterFragment : BaseFragment<FragmentCharacterBinding,CharacterViewModel>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_character
    override val viewModelClass: Class<CharacterViewModel>
        get() = CharacterViewModel::class.java


}