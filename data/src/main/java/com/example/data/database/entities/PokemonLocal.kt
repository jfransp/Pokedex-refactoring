package com.example.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
class PokemonLocal(
    val height: Int,
    val id: Int,
    @PrimaryKey
    val name: String,
    val weight: Int
)
