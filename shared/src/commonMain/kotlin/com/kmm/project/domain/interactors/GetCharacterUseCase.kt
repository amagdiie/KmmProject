package com.kmm.project.domain.interactors

import com.kmm.project.domain.IRepository
import com.kmm.project.domain.interactors.type.BaseUseCase
import com.kmm.project.domain.model.Character
import kotlinx.coroutines.CoroutineDispatcher

class GetCharacterUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Int, Character>(dispatcher){
    override suspend fun block(param: Int): Character = repository.getCharacter(param)
}