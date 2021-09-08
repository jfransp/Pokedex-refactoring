package com.example.domain.usecases

import com.example.domain.models.pokemondetails.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface getPokemonDetailsUseCase {

    fun getPokemonDetails(): Flow<PokemonDetails>

}