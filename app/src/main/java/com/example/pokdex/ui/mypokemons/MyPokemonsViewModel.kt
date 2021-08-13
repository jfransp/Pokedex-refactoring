package com.example.pokdex.ui.mypokemons

import androidx.lifecycle.*
import com.example.pokdex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPokemonsViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    val pokemonLiveData = repository.getAllSavedPokemon()

}
