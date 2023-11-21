package com.kmm.project.presentation.ui.features.character_detail

import com.kmm.project.domain.model.Character
import com.kmm.project.presentation.model.ResourceUiState
import com.kmm.project.presentation.mvi.UiEffect
import com.kmm.project.presentation.mvi.UiEvent
import com.kmm.project.presentation.mvi.UiState

interface CharacterDetailContract {
    sealed interface Event : UiEvent {
        object OnFavoriteClick : Event
        object OnTryCheckAgainClick : Event
        object OnBackPressed : Event
    }

    data class State(
        val character: ResourceUiState<Character>,
        val isFavorite: ResourceUiState<Boolean>,
    ) : UiState

    sealed interface Effect : UiEffect {
        object CharacterAdded : Effect
        object CharacterRemoved : Effect
        object BackNavigation : Effect
    }
}