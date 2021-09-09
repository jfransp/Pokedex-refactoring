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

    override suspend fun getPokemonDetailsListFromLocal(): List<PokemonDetails> =
        localDataSource.getPokemonDetailsList()


    override suspend fun getPokemonDetailsFromRemote(pokemonName: String): PokemonDetails =
        remoteDataSource.getPokemonDetails(pokemonName)

    override suspend fun getPokemonDetailsFromLocal(pokemonName: String): PokemonDetails? =
        localDataSource.getPokemonDetails(pokemonName)


    override suspend fun savePokemon(pokemon: PokemonDetails) {
        localDataSource.insertPokemon(pokemon)
    }

    override suspend fun deletePokemon(pokemonName: String) {
        localDataSource.deletePokemon(pokemonName)
    }

}
