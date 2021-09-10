package com.example.pokdex.ui.mypokemons

import androidx.lifecycle.*
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.usecases.DeleteSavedPokemonUseCase
import com.example.domain.usecases.GetSavedPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPokemonsViewModel @Inject constructor(
    private val deleteSavedPokemonUseCase: DeleteSavedPokemonUseCase,
    private val getSavedPokemonListUseCase: GetSavedPokemonListUseCase
): ViewModel() {

    val pokemonListLiveData: Nothing = TODO()

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