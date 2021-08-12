package com.example.pokdex.ui.pokedexmain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokdex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokedexMainViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    val pokemonListFlow = repository.getAllPokemon().asLiveData().cachedIn(viewModelScope)

}