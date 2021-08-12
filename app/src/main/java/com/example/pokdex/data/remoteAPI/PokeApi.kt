package com.example.pokdex.data.remoteAPI

import com.example.pokdex.data.models.pokemondetails.PokemonDetails
import com.example.pokdex.data.models.pokemonlist.PokemonListResponse
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
    ) : PokemonListResponse

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetails(
        @Path("pokemonName")
        pokemonName: String
    ) : PokemonDetails

}