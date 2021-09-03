package com.example.domain.models.pokemondetails

data class PokemonDetails(
    val height: Int,
    val id: Int,
    val name: String,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)
