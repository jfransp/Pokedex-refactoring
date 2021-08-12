package com.example.pokdex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pokdex.data.remoteAPI.PokeApi
import com.example.pokdex.paging.PokemonListPagingSource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {

    fun getAllPokemon() = Pager(
        config = PagingConfig(
            pageSize = 50,
            maxSize = 1200,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PokemonListPagingSource(
            api
        ) }
    ).flow

     suspend fun getPokemonDetails(pokemonName: String) =
        api.getPokemonDetails(pokemonName = pokemonName)

}