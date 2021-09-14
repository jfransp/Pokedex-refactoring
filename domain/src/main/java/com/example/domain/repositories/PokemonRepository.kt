package com.example.domain.repositories

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.models.pokemonlist.PokemonList
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonListFromRemote(limit: Int, offset: Int): PokemonList

    suspend fun getPokemonDetailsFromRemote(pokemonName: String): PokemonDetails

    suspend fun savePokemon(pokemon: PokemonDetails)

    suspend fun deletePokemon(pokemonName: String)

    suspend fun getPokemonDetailsListFromLocal(): Flow<List<PokemonDetails>>

    suspend fun getPokemonDetailsFromLocal(pokemonName: String): PokemonDetails?

}
