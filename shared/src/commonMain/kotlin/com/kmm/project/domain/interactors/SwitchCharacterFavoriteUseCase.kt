package com.kmm.project.domain.interactors

import com.kmm.project.domain.IRepository
import com.kmm.project.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SwitchCharacterFavoriteUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Int, Boolean>(dispatcher) {
    override suspend fun block(param: Int): Boolean {
        if (repository.isCharacterFavorite(param)) {
            repository.removeCharacterFromFavorite(param)
        } else {
            repository.addCharacterToFavorites(repository.getCharacter(param))
        }
        return repository.isCharacterFavorite(param)
    }
}