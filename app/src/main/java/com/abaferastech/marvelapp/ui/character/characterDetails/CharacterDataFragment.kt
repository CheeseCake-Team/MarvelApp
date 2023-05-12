package com.abaferastech.marvelapp.ui.character.characterDetails


import android.os.Bundle
import android.view.View
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.databinding.FragmentCharacterBinding
import com.abaferastech.marvelapp.ui.base.BaseFragment
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.utils.Constants


class CharacterDataFragment : BaseFragment<FragmentCharacterBinding, CharacterDetailsViewModel>() {

    override val layoutIdFragment: Int
        get() = R.layout.fragment_character_data
    override val viewModelClass: Class<CharacterDetailsViewModel>
        get() = CharacterDetailsViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeID = arguments?.getInt(Constants.TYPE_ID)
        viewModel.getSingleCharacter( typeID!! )
    }

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