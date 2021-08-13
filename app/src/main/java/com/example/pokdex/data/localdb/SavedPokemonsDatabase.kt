package com.example.pokdex.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokdex.data.models.pokemondetails.PokemonDetails
import com.example.pokdex.util.DatabaseTypeConverters

@Database(
    entities = [PokemonDetails::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseTypeConverters::class)
abstract class SavedPokemonsDatabase: RoomDatabase() {

    abstract fun getDao(): SavedPokemonsDao
}
