package com.example.pokdex.data.localdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pokdex.data.models.pokemondetails.PokemonDetails

@Dao
interface SavedPokemonsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonDetails): Long

    @Query("SELECT * FROM saved_pokemon_data")
    fun getAllSavedPokemon(): LiveData<List<PokemonDetails>>

    @Query("SELECT * FROM saved_pokemon_data WHERE id LIKE :pokemonId")
    suspend fun getSavedPokemon(pokemonId: Int) : PokemonDetails

    @Query("DELETE FROM saved_pokemon_data WHERE id LIKE :pokemonId")
    suspend fun deletePokemon(pokemonId: Int)

}
