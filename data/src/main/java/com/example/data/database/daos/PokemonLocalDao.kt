package com.example.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entities.PokemonLocal

@Dao
interface PokemonLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonLocal(pokemon: PokemonLocal)

    @Query("DELETE FROM pokemons WHERE id LIKE :pokemonId")
    suspend fun deletePokemonLocal(pokemonId: Int)

    @Query("SELECT * FROM pokemons")
    suspend fun getAllPokemonLocal(): List<PokemonLocal>

    @Query("SELECT * FROM pokemons WHERE id LIKE :pokemonId")
    suspend fun getPokemonLocal(pokemonId: Int): PokemonLocal

}