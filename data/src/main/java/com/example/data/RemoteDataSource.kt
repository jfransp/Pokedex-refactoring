package com.example.data

import com.example.data.remoteAPI.PokeApi
import com.example.domain.models.pokemonlist.PokemonList
import com.example.mappers.MapperImpl

class RemoteDataSource(
    private val api: PokeApi,
    private val mapper: MapperImpl
) {

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonList =
        mapper.mapPokemonListRemoteToPokemonList(api.getAllPokemon(limit, offset))

    suspend fun getPokemonDetails(pokemonName: String) =
        mapper.mapPokemonDetailsRemoteToPokemonDetails(api.getPokemonDetails(pokemonName))

}
