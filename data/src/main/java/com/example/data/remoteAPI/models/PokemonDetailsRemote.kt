package com.example.data.remoteAPI.models


data class PokemonDetailsRemote(
    val height: Int,
    val id: Int,
    val name: String,
    val stats: List<StatRemote>,
    val types: List<TypeRemote>,
    val weight: Int
)
