package com.example.pokdex.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.models.pokemonlist.Pokemon
import com.example.domain.usecases.GetPokemonListUseCase
import com.example.domain.util.ErrorEntity
import com.example.domain.util.Resource
import com.example.pokdex.util.Constants.Companion.PAGING_STARTING_OFFSET_INDEX

class PokemonListPagingSource(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val loadStatHandler: OnPagingLoadStat
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {

        val offsetValue = params.key ?: PAGING_STARTING_OFFSET_INDEX

        val response = getPokemonListUseCase.getPokemonList(
            params.loadSize,
            offsetValue
        )

        return when (response) {
            is Resource.Error -> {
                loadStatHandler.error(response.error)
                LoadResult.Error(Exception())
            }
            is Resource.Success -> {
                loadStatHandler.success()
                val data = response.data.results
                LoadResult.Page(
                    data = data,
                    prevKey = if (offsetValue == PAGING_STARTING_OFFSET_INDEX) null else offsetValue - params.loadSize,
                    nextKey = if (data.isEmpty()) null else offsetValue + params.loadSize
                )
            }
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }

    interface OnPagingLoadStat {
        fun success()
        fun error(error: ErrorEntity)
    }

}
