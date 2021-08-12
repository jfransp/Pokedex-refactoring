package com.example.pokdex.util

import com.example.pokdex.data.models.pokemondetails.PokemonDetails
import retrofit2.Response

fun handlePokemonResponse(pokemonDetailsResponse: Response<PokemonDetails>) : Resource<PokemonDetails> {
    if (pokemonDetailsResponse.isSuccessful) {
        pokemonDetailsResponse.body()?.let { pokemonResponse ->
            return Resource.Success(pokemonResponse)
        }
    }
    return Resource.Error(pokemonDetailsResponse.message())
}