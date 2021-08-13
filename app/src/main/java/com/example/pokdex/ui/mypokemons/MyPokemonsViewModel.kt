package com.example.pokdex.ui.mypokemons

import androidx.lifecycle.*
import com.example.pokdex.data.models.pokemondetails.PokemonDetails
import com.example.pokdex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPokemonsViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    val pokemonLiveData = repository.getAllSavedPokemon()

    fun deletePokemon(pokemonId: Int) {
        viewModelScope.launch {
            repository.deletePokemon(pokemonId)
        }
    }

    fun undoDeletion(pokemon: PokemonDetails) {
        viewModelScope.launch {
            repository.upsertPokemon(pokemon)
        }
    }

}
