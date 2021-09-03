package com.example.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
class PokemonLocal(
    val height: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    val weight: Int
)
