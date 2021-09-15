package com.example.data.remoteAPI.service

import com.example.data.remoteAPI.KtorHttpClient.AppClient
import com.example.data.remoteAPI.models.PokemonDetailsRemote
import com.example.data.remoteAPI.models.PokemonListRemote
import com.example.util.Constants.Companion.BASE_URL
import io.ktor.client.request.*

class PokeApiImpl(private val appClient: AppClient): PokeApi {

    override suspend fun getAllPokemon(limit: Int, offset: Int): PokemonListRemote =
        appClient.client.get<PokemonListRemote>(BASE_URL + "pokemon") {
            parameter("limit", limit)
            parameter("offset", offset)
        }

    override suspend fun getPokemonDetails(pokemonName: String): PokemonDetailsRemote =
        appClient.client.get<PokemonDetailsRemote>(BASE_URL + "pokemon/" + pokemonName)

}
