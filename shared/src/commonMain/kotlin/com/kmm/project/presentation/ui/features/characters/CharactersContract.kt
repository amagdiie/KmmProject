package com.kmm.project.presentation.ui.features.characters

import com.kmm.project.domain.model.Character
import com.kmm.project.presentation.model.ResourceUiState
import com.kmm.project.presentation.mvi.UiEffect
import com.kmm.project.presentation.mvi.UiEvent
import com.kmm.project.presentation.mvi.UiState

interface CharactersContract {
    sealed interface Event : UiEvent {
        object OnTryCheckAgainClick : Event
        object OnFavoritesClick : Event
        data class OnCharacterClick(val idCharacter: Int) : Event
    }

    data class State(
        val characters: ResourceUiState<List<Character>>
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCharacter(val idCharacter: Int) : Effect
        object NavigateToFavorites : Effect
    }
}


