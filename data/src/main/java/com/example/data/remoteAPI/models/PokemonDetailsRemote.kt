package com.example.data.remoteAPI.models

import com.example.domain.models.pokemondetails.Stat
import com.example.domain.models.pokemondetails.Type

data class PokemonDetailsRemote(
    val height: Int,
    val id: Int,
    val name: String,
    val stats: List<StatRemote>,
    val types: List<TypeRemote>,
    val weight: Int
)
