package com.example.data

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.models.pokemonlist.PokemonList
import com.example.domain.repositories.PokemonRepository

class PokemonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): PokemonRepository {

    override suspend fun getPokemonListFromRemote(limit: Int, offset: Int): PokemonList =
        remoteDataSource.getPokemonList(limit, offset)

    override suspend fun getPokemonDetailsListFromLocal(): List<PokemonDetails> {
        localDataSource.getPokemonDetailsList()
        TODO()
    }

    override suspend fun getPokemonDetailsFromRemote(pokemonName: String): PokemonDetails =
        remoteDataSource.getPokemonDetails(pokemonName)

    override suspend fun getPokemonDetailsFromLocal(pokemonName: String): PokemonDetails =
        localDataSource.getPokemonDetails(pokemonName)


    override suspend fun savePokemon(pokemon: PokemonDetails) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePokemon(pokemonId: Int) {
        TODO("Not yet implemented")
    }

}
