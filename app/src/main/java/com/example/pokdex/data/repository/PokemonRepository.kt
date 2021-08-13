package com.example.pokdex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pokdex.data.localdb.SavedPokemonsDatabase
import com.example.pokdex.data.models.pokemondetails.PokemonDetails
import com.example.pokdex.data.remoteAPI.PokeApi
import com.example.pokdex.paging.PokemonListPagingSource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi,
    private val db: SavedPokemonsDatabase
) {

    //Remote API functions on Retrofit
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


    //Local functions on Room database
    suspend fun savePokemon(pokemon: PokemonDetails) {
        db.getDao().insert(pokemon)
    }

    fun getAllSavedPokemon() =
        db.getDao().getAllSavedPokemon()

    suspend fun getSavedPokemon(pokemonId: Int) =
        db.getDao().getSavedPokemon(pokemonId)


    suspend fun deletePokemon(id: Int) {
        db.getDao().deletePokemon(id)
    }

}
