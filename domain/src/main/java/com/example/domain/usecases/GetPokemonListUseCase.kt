package com.example.domain.usecases

import com.example.domain.models.pokemonlist.PokemonList
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetPokemonListUseCase {

    fun getPokemonList(): Resource<PokemonList> {
        TODO()
    }

}
