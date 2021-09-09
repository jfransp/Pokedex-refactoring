package com.example.domain.repositories

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.models.pokemonlist.PokemonList

interface PokemonRepository {

    suspend fun getPokemonListFromRemote(limit: Int, offset: Int): PokemonList

    suspend fun getPokemonDetailsFromRemote(): PokemonDetails

    suspend fun savePokemon(pokemon: PokemonDetails)

    suspend fun deletePokemon(pokemonId: Int)

    suspend fun getPokemonDetailsListFromLocal()

    suspend fun getPokemonDetailsFromLocal(): PokemonDetails

}
