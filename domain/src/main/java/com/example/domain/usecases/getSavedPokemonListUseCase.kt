package com.example.domain.usecases

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.repositories.PokemonRepository
import com.example.domain.util.ErrorHandler
import com.example.domain.util.Resource
import java.lang.Exception

class getSavedPokemonListUseCase(
    private val repository: PokemonRepository,
    private val errorHandler: ErrorHandler
) {

    //The errorHandler doesn't implement local room errors yet, so it will always return an unknown
    //error in case of an exception

    suspend fun getSavedPokemonList(): Resource<List<PokemonDetails>> {
        val output: Resource<List<PokemonDetails>> = try {
            val result = repository.getPokemonDetailsListFromLocal()
            Resource.Success(result)
        } catch (e: Exception) {
            val error = errorHandler.getError(e)
            Resource.Error(error)
        }
        return output
    }

}
