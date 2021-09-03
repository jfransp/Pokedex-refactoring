package com.example.data

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.models.pokemonlist.PokemonList
import com.example.domain.repositories.PokemonRepository

class PokemonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): PokemonRepository {

    override suspend fun getAllFromRemote(): PokemonList {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonDetails(): PokemonDetails {
        TODO("Not yet implemented")
    }

    override suspend fun upsert(pokemon: PokemonDetails) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFromLocal() {
        TODO("Not yet implemented")
    }
}
