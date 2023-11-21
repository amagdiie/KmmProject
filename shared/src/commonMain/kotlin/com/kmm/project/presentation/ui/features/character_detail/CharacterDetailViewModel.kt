package com.kmm.project.presentation.ui.features.character_detail

import cafe.adriel.voyager.core.model.coroutineScope
import com.kmm.project.domain.interactors.GetCharacterUseCase
import com.kmm.project.domain.interactors.IsCharacterFavoriteUseCase
import com.kmm.project.domain.interactors.SwitchCharacterFavoriteUseCase
import com.kmm.project.presentation.mvi.BaseViewModel
import com.kmm.project.presentation.model.ResourceUiState
import com.kmm.project.presentation.ui.features.character_detail.CharacterDetailContract
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase,
    private val switchCharacterFavoriteUseCase: SwitchCharacterFavoriteUseCase,
    private val characterId: Int,
) : BaseViewModel<CharacterDetailContract.Event, CharacterDetailContract.State, CharacterDetailContract.Effect>() {

    init {
        getCharacter(characterId)
        checkIfIsFavorite(characterId)
    }

    override fun createInitialState(): CharacterDetailContract.State =
        CharacterDetailContract.State(
            character = ResourceUiState.Idle,
            isFavorite = ResourceUiState.Idle,
        )

    override fun handleEvent(event: CharacterDetailContract.Event) {
        when (event) {
            CharacterDetailContract.Event.OnFavoriteClick -> switchCharacterFavorite(characterId)
            CharacterDetailContract.Event.OnTryCheckAgainClick -> getCharacter(characterId)
            CharacterDetailContract.Event.OnBackPressed -> setEffect { CharacterDetailContract.Effect.BackNavigation }
        }
    }

    private fun getCharacter(characterId: Int) {
        setState { copy(character = ResourceUiState.Loading) }
        coroutineScope.launch {
            getCharacterUseCase(characterId)
                .onSuccess { setState { copy(character = ResourceUiState.Success(it)) } }
                .onFailure { setState { copy(character = ResourceUiState.Error()) } }
        }
    }

    private fun checkIfIsFavorite(idCharacter: Int) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        coroutineScope.launch {
            isCharacterFavoriteUseCase(idCharacter)
                .onSuccess { setState { copy(isFavorite = ResourceUiState.Success(it)) } }
                .onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }

    private fun switchCharacterFavorite(idCharacter: Int) {
        setState { copy(isFavorite = ResourceUiState.Loading) }
        coroutineScope.launch {
            switchCharacterFavoriteUseCase(idCharacter)
                .onSuccess {
                    setState { copy(isFavorite = ResourceUiState.Success(it)) }
                    setEffect { if (!it) CharacterDetailContract.Effect.CharacterRemoved else CharacterDetailContract.Effect.CharacterAdded}
                }.onFailure { setState { copy(isFavorite = ResourceUiState.Error()) } }
        }
    }
}