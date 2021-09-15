package com.example.data.remoteAPI.service

import com.example.data.remoteAPI.models.PokemonDetailsRemote
import com.example.data.remoteAPI.models.PokemonListRemote

interface PokeApi {

    suspend fun getAllPokemon(limit: Int, offset: Int): PokemonListRemote
    suspend fun getPokemonDetails(pokemonName: String): PokemonDetailsRemote

}
