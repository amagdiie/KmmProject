package com.kmm.project.domain.interactors

import com.kmm.project.domain.IRepository
import com.kmm.project.domain.interactors.type.BaseUseCase
import com.kmm.project.domain.model.Character
import kotlinx.coroutines.CoroutineDispatcher

class GetCharactersUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Unit, List<Character>>(dispatcher){
    override suspend fun block(param: Unit): List<Character> = repository.getCharacters()
}