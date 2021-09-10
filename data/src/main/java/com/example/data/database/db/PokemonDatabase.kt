package com.example.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.daos.PokemonLocalDao
import com.example.data.database.daos.StatLocalDao
import com.example.data.database.daos.TypeLocalDao
import com.example.data.database.entities.PokemonLocal
import com.example.data.database.entities.StatLocal
import com.example.data.database.entities.TypeLocal

@Database (
    entities = [PokemonLocal::class, StatLocal::class, TypeLocal::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun pokemonLocalDao(): PokemonLocalDao
    abstract fun statLocalDao(): StatLocalDao
    abstract fun typeLocalDao(): TypeLocalDao

}
