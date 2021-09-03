package com.example.domain.repositories

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.models.pokemonlist.PokemonList

interface PokemonRepository {

    suspend fun getAllFromRemote(): PokemonList

    suspend fun getPokemonDetails(): PokemonDetails

    suspend fun upsert(pokemon: PokemonDetails)

    suspend fun getAllFromLocal()

}
