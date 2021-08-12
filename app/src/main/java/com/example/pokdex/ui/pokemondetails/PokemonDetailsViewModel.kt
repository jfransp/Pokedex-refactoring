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

    val pokemon = state.get<Pokemon>("pokemon")!!

    private var _result = MutableLiveData<PokemonDetails>()
    val pokemonLiveData: LiveData<PokemonDetails> = _result

    fun getPokemonDetails() = viewModelScope.launch {
        _result.value = repository.getPokemonDetails(pokemon.name)
    }

    fun savePokemon() {
        TODO()
    }

}