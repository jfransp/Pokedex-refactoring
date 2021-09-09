package com.example.pokdex.di

import com.example.domain.repositories.PokemonRepository
import com.example.domain.usecases.DeleteSavedPokemonUseCase
import com.example.domain.usecases.GetPokemonDetailsUseCase
import com.example.domain.usecases.GetSavedPokemonListUseCase
import com.example.domain.usecases.SavePokemonUseCase
import com.example.domain.util.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideDeletePokemonUseCase(repository: PokemonRepository) = DeleteSavedPokemonUseCase(repository)

    @Provides
    @Singleton
    fun provideGetPokemonDetailsUseCase(
        repository: PokemonRepository,
        errorHandler: ErrorHandler
    ) = GetPokemonDetailsUseCase(repository, errorHandler)

    @Provides
    @Singleton
    fun provideGetPokemonListUseCase(
        repository: PokemonRepository,
        errorHandler: ErrorHandler
    ) = GetPokemonDetailsUseCase(repository, errorHandler)

    @Provides
    @Singleton
    fun provideGetSavedPokemonListUseCase(
        repository: PokemonRepository,
        errorHandler: ErrorHandler
    ) = GetSavedPokemonListUseCase(repository, errorHandler)

    @Provides
    @Singleton
    fun provideSavePokemonUseCase(
        repository: PokemonRepository
    ) = SavePokemonUseCase(repository)

}
