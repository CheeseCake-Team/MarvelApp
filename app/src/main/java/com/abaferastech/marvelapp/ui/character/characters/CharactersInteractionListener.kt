package com.abaferastech.marvelapp.ui.character.characters

import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

interface CharactersInteractionListener:BaseInteractionListener {
   fun  onClickCharacter(character: Character)

}
