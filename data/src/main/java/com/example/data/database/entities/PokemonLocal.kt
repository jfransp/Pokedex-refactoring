package com.example.data.database.entities

import androidx.room.Entity

@Entity(tableName = "pokemon")
class PokemonLocal(
    val height: Int,
    val id: Int,
    val name: String,
    val weight: Int
)