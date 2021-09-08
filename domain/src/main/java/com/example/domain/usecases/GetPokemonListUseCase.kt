package com.example.domain.usecases

import com.example.domain.models.pokemonlist.PokemonList
import kotlinx.coroutines.flow.Flow

interface GetPokemonListUseCase {

    fun getPokemonList(): Flow<PokemonList>

}
