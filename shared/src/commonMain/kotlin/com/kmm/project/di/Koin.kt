package com.kmm.project.di

import com.kmm.project.data_cache.CacheDataImp
import com.kmm.project.data_cache.sqldelight.SharedDatabase
import com.kmm.project.data_remote.RemoteDataImp
import com.kmm.project.data_remote.model.mapper.ApiCharacterMapper
import com.kmm.project.domain.IRepository
import com.kmm.project.domain.interactors.GetCharacterUseCase
import com.kmm.project.domain.interactors.GetCharactersFavoritesUseCase
import com.kmm.project.domain.interactors.GetCharactersUseCase
import com.kmm.project.domain.interactors.IsCharacterFavoriteUseCase
import com.kmm.project.domain.interactors.SwitchCharacterFavoriteUseCase
import com.kmm.project.repository.ICacheData
import com.kmm.project.repository.IRemoteData
import com.kmm.project.repository.RepositoryImp
import com.kmm.project.presentation.ui.features.character_detail.CharacterDetailViewModel
import com.kmm.project.presentation.ui.features.characters.CharactersViewModel
import com.kmm.project.presentation.ui.features.characters_favorites.CharactersFavoritesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            useCasesModule,
            repositoryModule,
            ktorModule,
            sqlDelightModule,
            mapperModule,
            dispatcherModule,
            platformModule()
        )
    }

val viewModelModule = module {
    factory { CharactersViewModel(get()) }
    factory { CharactersFavoritesViewModel(get()) }
    factory { params -> CharacterDetailViewModel(get(), get(), get(), params.get()) }
}

val useCasesModule: Module = module {
    factory { GetCharactersUseCase(get(), get()) }
    factory { GetCharactersFavoritesUseCase(get(), get()) }
    factory { GetCharacterUseCase(get(), get()) }
    factory { IsCharacterFavoriteUseCase(get(), get()) }
    factory { SwitchCharacterFavoriteUseCase(get(), get()) }
}

val repositoryModule = module {
    single<IRepository> { RepositoryImp(get(), get()) }
    single<ICacheData> { CacheDataImp(get()) }
    single<IRemoteData> { RemoteDataImp(get(), get(), get()) }


}

val ktorModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    single { "https://rickandmortyapi.com" }
}

val sqlDelightModule = module {
    single { SharedDatabase(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val mapperModule = module {
    factory { ApiCharacterMapper() }
}

fun initKoin() = initKoin {}



