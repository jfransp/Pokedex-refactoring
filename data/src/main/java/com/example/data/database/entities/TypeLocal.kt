package com.example.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "types")
class TypeLocal(
    val slot: Int,
    val type: String,
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val type_id: Int? = null
)