package com.kmm.project.repository

import com.kmm.project.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ICacheData {
    suspend fun addCharacterToFavorite(character: Character)
    suspend fun removeCharacterFromFavorite(idCharacter: Int)
    suspend fun getAllCharacterFavorites(): Flow<List<Character>>
    suspend fun isCharacterFavorite(idCharacter: Int): Boolean
}