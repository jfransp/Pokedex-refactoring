package com.example.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
class TypeLocal(
    val slot: Int,
    val type: String,
    @PrimaryKey
    val pokemon_id: Int? = null
)