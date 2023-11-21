package com.kmm.project.presentation.ui.features.characters_favorites

import com.kmm.project.domain.model.Character
import com.kmm.project.presentation.model.ResourceUiState
import com.kmm.project.presentation.mvi.UiEffect
import com.kmm.project.presentation.mvi.UiEvent
import com.kmm.project.presentation.mvi.UiState

interface CharactersFavoritesContract {
    sealed interface Event : UiEvent {
        object OnBackPressed : Event
        object OnTryCheckAgainClick : Event
        data class OnCharacterClick(val idCharacter: Int) : Event
    }

    data class State(
        val charactersFavorites: ResourceUiState<List<Character>>,
    ) : UiState

    sealed interface Effect : UiEffect {
        data class NavigateToDetailCharacter(val idCharacter: Int) : Effect
        object BackNavigation : Effect

    }
}