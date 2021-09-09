package com.example.domain.usecases

import com.example.domain.models.pokemonlist.PokemonList
import com.example.domain.repositories.PokemonRepository
import com.example.domain.util.ErrorHandler
import com.example.domain.util.Resource

class GetPokemonListUseCase(
    private val repository: PokemonRepository,
    private val errorHandler: ErrorHandler
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val output: Resource<PokemonList> = try {
            val response = repository.getPokemonListFromRemote(limit, offset)
            Resource.Success(response)
        } catch (e: Exception) {
            val error = errorHandler.getError(e)
            Resource.Error(error)
        }
        return output
    }

}
