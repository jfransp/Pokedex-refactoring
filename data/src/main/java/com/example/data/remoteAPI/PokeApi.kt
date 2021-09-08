package com.example.data.remoteAPI

import com.example.data.remoteAPI.models.PokemonListResponse
import com.example.data.remoteAPI.models.PokemonResponse
import com.example.domain.models.pokemonlist.PokemonList
import retrofit2.Response
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
    ) : Response<PokemonListResponse>

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetails(
        @Path("pokemonName")
        pokemonName: String
    ) : Response<PokemonResponse>

}
