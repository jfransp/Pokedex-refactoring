package com.example.domain.usecases

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.repositories.PokemonRepository
import com.example.domain.util.ErrorHandler
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetSavedPokemonListUseCase(
    private val repository: PokemonRepository,
    private val errorHandler: ErrorHandler
) {

    //The errorHandler doesn't implement local room errors yet, so it will always return an unknown
    //error in case of an exception

    fun getSavedPokemonList(): Flow<Resource<List<PokemonDetails>>> {
        return flow {
            try {
                repository.getPokemonDetailsListFromLocal().collect { pokemonDetailsList ->
                    emit(Resource.Success(pokemonDetailsList))
                }
            } catch (e: Exception) {
                val error = errorHandler.getError(e)
                emit(Resource.Error(error))
            }
        }
    }

}
