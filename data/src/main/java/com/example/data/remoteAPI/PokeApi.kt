package com.example.data.remoteAPI

import com.example.data.remoteAPI.models.PokemonDetailsRemote
import com.example.data.remoteAPI.models.PokemonListRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("limit")
        limit: Int,
        @Query("offset")
        offset: Int
    ) : PokemonListRemote

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetails(
        @Path("pokemonName")
        pokemonName: String
    ) : PokemonDetailsRemote

}
