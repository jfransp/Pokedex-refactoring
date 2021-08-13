package com.example.pokdex.ui.pokemondetails

import androidx.lifecycle.*
import com.example.pokdex.data.models.pokemondetails.PokemonDetails
import com.example.pokdex.data.models.pokemonlist.Pokemon
import com.example.pokdex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository,
    state: SavedStateHandle
): ViewModel() {

    val pokemon = state.get<Pokemon>("pokemon")
    private val pokemonId = state.get<Int>("pokemonId")
    private val isFetchFromRemote = state.get<Boolean>("isFetchFromRemote")

    private var _result = MutableLiveData<PokemonDetails>()
    val pokemonLiveData: LiveData<PokemonDetails> = _result


    fun fetchData() {
        if (isFetchFromRemote == true) {
            getPokemonDetails()
        } else {
            getLocalPokemon()
        }
    }

    private fun getPokemonDetails() = viewModelScope.launch {
        _result.value = pokemon?.name?.let { repository.getPokemonDetails(it) }
    }

    private fun getLocalPokemon() = viewModelScope.launch {
        if (pokemonId != 0) {
            _result.value = pokemonId?.let { repository.getSavedPokemon(it) }
        }
    }


    fun savePokemon(pokemon: PokemonDetails) {
        viewModelScope.launch {
            repository.savePokemon(pokemon)
        }
    }

}
