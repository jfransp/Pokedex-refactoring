package com.example.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats")
class StatLocal(
    val base_stat: Int,
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val stat_id: Int? = null
)