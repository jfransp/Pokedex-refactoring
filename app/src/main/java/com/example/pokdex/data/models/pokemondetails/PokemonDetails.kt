package com.example.pokdex.data.models.pokemondetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "saved_pokemon_data"
)
data class PokemonDetails(
    val height: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) : Serializable