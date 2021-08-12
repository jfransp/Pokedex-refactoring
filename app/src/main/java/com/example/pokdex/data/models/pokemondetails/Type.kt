package com.example.pokdex.data.models.pokemondetails

import java.io.Serializable

data class Type(
    val slot: Int,
    val type: TypeX
) : Serializable