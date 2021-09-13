package com.example.pokdex.di

import com.example.domain.usecases.*
import org.koin.dsl.module

val useCasesModule = module {

    single { DeleteSavedPokemonUseCase(repository = get()) }

    single {
        GetPokemonDetailsUseCase(
        repository = get(),
        errorHandler = get())
    }

    single {
        GetPokemonListUseCase(
            repository = get(),
            errorHandler = get()
        )
    }

    single {
        GetSavedPokemonListUseCase(
            repository = get(),
            errorHandler = get()
        )
    }

    single { SavePokemonUseCase(repository = get()) }

}
