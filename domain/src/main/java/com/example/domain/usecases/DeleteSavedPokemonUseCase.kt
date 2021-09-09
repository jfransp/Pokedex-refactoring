package com.example.domain.usecases

import com.example.domain.repositories.PokemonRepository

class DeleteSavedPokemonUseCase(
    private val repository: PokemonRepository
) {

    suspend fun deleteSavedPokemon(pokemonName: String) {
        repository.deletePokemon(pokemonName)
    }

}