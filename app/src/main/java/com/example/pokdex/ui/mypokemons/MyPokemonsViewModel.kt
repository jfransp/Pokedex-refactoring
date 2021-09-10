package com.example.pokdex.ui.mypokemons

import androidx.lifecycle.*
import com.example.pokdex.data.models.pokemondetails.PokemonDetails
import com.example.pokdex.data.repository.PokemonRepositoryold
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPokemonsViewModel @Inject constructor(
): ViewModel() {

    val pokemonLiveData: Nothing = TODO()

    fun deletePokemon(pokemonId: Int) {
        viewModelScope.launch {
            TODO()
        }
    }

    fun undoDeletion(pokemon: PokemonDetails) {
        viewModelScope.launch {
            TODO()
        }
    }
}