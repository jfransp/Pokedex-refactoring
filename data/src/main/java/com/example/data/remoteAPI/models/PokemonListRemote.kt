package com.example.data.remoteAPI.models

import com.example.domain.models.pokemonlist.Pokemon

data class PokemonListRemote(
    val count: String,
    val next: Int,
    val preview: Any,
    val results: List<Pokemon>
)
