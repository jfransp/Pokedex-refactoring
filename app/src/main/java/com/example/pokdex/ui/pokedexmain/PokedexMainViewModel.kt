package com.example.pokdex.ui.pokedexmain

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.domain.usecases.GetPokemonListUseCase
import com.example.domain.util.ErrorEntity
import com.example.pokdex.paging.PokemonListPagingSource
import com.example.pokdex.util.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class PokedexMainViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
): ViewModel(), PokemonListPagingSource.OnPagingLoadState {

    private val _loadStateObservable: MutableStateFlow<LoadState?> = MutableStateFlow(null)
    val loadStateObservable = _loadStateObservable.asLiveData()

    val pokemonListLiveData = Pager(
        config = PagingConfig(
            pageSize = 50,
            maxSize = 1200,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PokemonListPagingSource(
            getPokemonListUseCase,
            this
        ) }
    ).liveData.cachedIn(viewModelScope)


    override fun success() {
        _loadStateObservable.value = LoadState.SUCCESS
    }

    override fun error(error: ErrorEntity) {
        _loadStateObservable.value = LoadState.ERROR(error)
    }

}
