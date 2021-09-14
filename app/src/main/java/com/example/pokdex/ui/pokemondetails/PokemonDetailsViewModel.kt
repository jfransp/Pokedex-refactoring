package com.example.pokdex.ui.pokemondetails

import androidx.lifecycle.*
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.usecases.GetPokemonDetailsUseCase
import com.example.domain.usecases.SavePokemonUseCase
import com.example.domain.util.ErrorEntity
import com.example.domain.util.Resource
import com.example.pokdex.util.LoadState
import com.example.pokdex.util.PokemonDetailsArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class PokemonDetailsViewModel(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val savePokemonUseCase: SavePokemonUseCase,
    private val pokemonDetailsArgs: PokemonDetailsArgs
): ViewModel() {

    private val pokemonName = pokemonDetailsArgs.pokemonName
    val isSavedPokemon = pokemonDetailsArgs.isSavedPokemon

    private val _loadStateObservable: MutableStateFlow<LoadState?> = MutableStateFlow(null)
    val loadStateObservable = _loadStateObservable.asLiveData()

    private var _result = MutableLiveData<PokemonDetails>()!!
    val pokemonLiveData: LiveData<PokemonDetails> = _result


    fun fetchData() = viewModelScope.launch {
        when (val response = pokemonName.let { getPokemonDetailsUseCase.getPokemonDetails(it) }) {
            is Resource.Error -> error(response.error)
            is Resource.Success -> {
                _result.value = response.data
                success()
            }
        }
    }


    fun savePokemon(pokemon: PokemonDetails) {
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
