package com.example.pokdex.data.models.pokemondetails

import androidx.room.Entity

@Entity(tableName = "saved_pokemon_data")
data class PokemonDetails(
    val height: Int,
    val id: Int,
    val name: String,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)