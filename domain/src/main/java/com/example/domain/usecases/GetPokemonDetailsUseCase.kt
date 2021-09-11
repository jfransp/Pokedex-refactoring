package com.example.domain.usecases

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.repositories.PokemonRepository
import com.example.domain.util.ErrorHandler
import com.example.domain.util.Resource

class GetPokemonDetailsUseCase(
    private val repository: PokemonRepository,
    private val errorHandler: ErrorHandler
) {

    suspend fun getPokemonDetails(pokemonName: String): Resource<PokemonDetails> {
        return try {
            val response = repository.getPokemonDetailsFromLocal(pokemonName)
                ?: repository.getPokemonDetailsFromRemote(pokemonName)
            Resource.Success(response)
        } catch (e: Exception) {
            val error = errorHandler.getError(e)
            Resource.Error(error)
        }
    }

}
