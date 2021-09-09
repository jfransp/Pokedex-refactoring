package com.example.domain.usecases

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.repositories.PokemonRepository

class SavePokemonUseCase(
    private val repository: PokemonRepository
) {

    suspend fun savePokemon(pokemon: PokemonDetails) {
        repository.savePokemon(pokemon)
    }

}
