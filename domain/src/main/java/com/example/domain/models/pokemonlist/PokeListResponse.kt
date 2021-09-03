package com.example.domain.models.pokemonlist

class PokeListResponse(
    val count: String,
    val next: Int,
    val preview: Any,
    val results: List<Pokemon>
)