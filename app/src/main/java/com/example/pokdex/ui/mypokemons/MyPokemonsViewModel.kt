package com.example.pokdex.ui.mypokemons

import androidx.lifecycle.*
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.usecases.DeleteSavedPokemonUseCase
import com.example.domain.usecases.GetSavedPokemonListUseCase
import com.example.domain.usecases.SavePokemonUseCase
import com.example.domain.util.ErrorEntity
import com.example.domain.util.Resource
import com.example.pokdex.util.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPokemonsViewModel @Inject constructor(
    private val deleteSavedPokemonUseCase: DeleteSavedPokemonUseCase,
    private val getSavedPokemonListUseCase: GetSavedPokemonListUseCase,
    private val savePokemonUseCase: SavePokemonUseCase
): ViewModel() {

    private var _result = MutableLiveData<List<PokemonDetails>>()!!
    val pokemonListLiveData: LiveData<List<PokemonDetails>> = _result

    private val _loadStateObservable: MutableStateFlow<LoadState?> = MutableStateFlow(null)
    val loadStateObservable = _loadStateObservable.asLiveData()

    fun fetchData() = viewModelScope.launch {
        when (val response = getSavedPokemonListUseCase.getSavedPokemonList()) {
            is Resource.Error -> error(response.error)
            is Resource.Success -> {
                _result.value = response.data
                success()
            }
        }
    }

    fun deletePokemon(pokemonName: String) {
        viewModelScope.launch {
            deleteSavedPokemonUseCase.deleteSavedPokemon(pokemonName)
        }
    }

    fun undoDeletion(pokemon: PokemonDetails) {
        viewModelScope.launch {
            savePokemonUseCase.savePokemon(pokemon)
        }
    }

    private fun success() {
        _loadStateObservable.value = LoadState.SUCCESS
    }

    private fun error(error: ErrorEntity) {
        _loadStateObservable.value = LoadState.ERROR(error)
    }

}
