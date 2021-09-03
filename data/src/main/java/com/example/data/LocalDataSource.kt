package com.example.data

import com.example.data.database.db.PokemonDatabase
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.mappers.Mapper

class LocalDataSource(
    val db: PokemonDatabase
) {

    suspend fun getAllPokemon(): List<PokemonDetails> {
        TODO()
    }

}