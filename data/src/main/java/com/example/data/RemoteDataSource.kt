package com.example.data

import com.example.data.remoteAPI.PokeApi
import com.example.domain.models.pokemonlist.PokemonList
import com.example.mappers.MapperImpl
import com.example.util.ErrorHandlerImpl

class RemoteDataSource(
    private val api: PokeApi,
    private val mapper: MapperImpl,
    private val errorHandler: ErrorHandlerImpl
) {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonList =
        mapper.mapPokemonListRemoteToPokemonList(api.getAllPokemon(limit, offset))

}
