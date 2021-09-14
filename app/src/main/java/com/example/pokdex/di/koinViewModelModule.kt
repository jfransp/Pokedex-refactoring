package com.example.pokdex.di

import com.example.pokdex.ui.mypokemons.MyPokemonsViewModel
import com.example.pokdex.ui.pokedexmain.PokedexMainViewModel
import com.example.pokdex.ui.pokemondetails.PokemonDetailsViewModel
import com.example.pokdex.util.PokemonDetailsArgs
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { PokedexMainViewModel(get()) }
    viewModel { (pokemonDetailsArgs: PokemonDetailsArgs) -> PokemonDetailsViewModel(
        getPokemonDetailsUseCase = get(),
        savePokemonUseCase = get(),
        pokemonDetailsArgs = pokemonDetailsArgs
    )}
    viewModel { MyPokemonsViewModel(get(), get(), get()) }

}
