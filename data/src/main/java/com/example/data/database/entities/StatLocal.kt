package com.example.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stat")
class StatLocal(
    val base_stat: Int,
    val poke_id: Int,
    @PrimaryKey(autoGenerate = true)
    val type_id: Int? = null
)