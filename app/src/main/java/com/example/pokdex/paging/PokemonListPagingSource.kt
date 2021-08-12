package com.example.pokdex.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokdex.data.models.pokemonlist.Pokemon
import com.example.pokdex.data.remoteAPI.PokeApi
import com.example.pokdex.util.Constants.Companion.PAGING_STARTING_OFFSET_INDEX

class PokemonListPagingSource(
    private val api: PokeApi
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {

        val offsetValue = params.key ?: PAGING_STARTING_OFFSET_INDEX

        return try {

            val response = api.getAllPokemon(
                params.loadSize,
                offsetValue
            )

            LoadResult.Page(
                data = response.results,
                prevKey = if (offsetValue == PAGING_STARTING_OFFSET_INDEX) null else offsetValue - params.loadSize,
                nextKey = if (response.results.isEmpty()) null else offsetValue + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

}
