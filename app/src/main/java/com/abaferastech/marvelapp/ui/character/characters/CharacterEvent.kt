package com.abaferastech.marvelapp.ui.character.characters


sealed class CharacterEvent {
    data class ClickCharacterEvent(val characterID: Int) : CharacterEvent()
}

