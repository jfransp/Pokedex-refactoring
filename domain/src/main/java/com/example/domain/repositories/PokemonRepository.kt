package com.example.domain.repositories

import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.models.pokemonlist.PokeListResponse

interface PokemonRepository {

    suspend fun getAllFromRemote(): PokeListResponse

    suspend fun getPokemonDetails(): PokemonDetails

    suspend fun upsert(pokemon: PokemonDetails)

    suspend fun getAllFromLocal()

}
