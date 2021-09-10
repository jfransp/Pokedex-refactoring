package com.example.data.remoteAPI.models


data class PokemonListRemote(
    val count: String,
    val next: String,
    val previous: Any?,
    val results: List<PokemonRemote>
)
