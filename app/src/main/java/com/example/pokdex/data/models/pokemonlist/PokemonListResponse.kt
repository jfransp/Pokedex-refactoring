package com.example.pokdex.data.models.pokemonlist

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Pokemon>
)
