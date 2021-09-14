package com.example.pokdex.di

import android.app.Application
import androidx.room.Room
import com.example.data.LocalDataSource
import com.example.data.PokemonRepositoryImpl
import com.example.data.RemoteDataSource
import com.example.data.database.db.PokemonDatabase
import com.example.domain.repositories.PokemonRepository
import com.example.domain.util.ErrorHandler
import com.example.mappers.Mapper
import com.example.mappers.MapperImpl
import com.example.util.ErrorHandlerImpl
import org.koin.dsl.module

val appModule = module {

    //Application
    single { Application() }

    //Mapper and ErrorHandler
    factory<Mapper> { MapperImpl() }
    factory<ErrorHandler> { ErrorHandlerImpl() }

    //Room Database and DAOs
    single { Room.databaseBuilder(get(), PokemonDatabase::class.java, "saved_pokemon_data")
        .fallbackToDestructiveMigration()
        .build()
    }

    factory { provideStatLocalDao(pokemonDatabase = get()) }
    factory { provideTypeLocalDao(pokemonDatabase = get()) }
    factory { providePokemonLocalDao(pokemonDatabase = get())}

    //DataSources and Repository
    single<PokemonRepository> { PokemonRepositoryImpl(
        localDataSource = get(),
        remoteDataSource = get()
    ) }

    factory { LocalDataSource(
        database = get(),
        mapper = get(),
        pokeDao = get(),
        typeDao = get(),
        statDao = get()
    ) }
    factory { RemoteDataSource(
        api = get(),
        mapper = get()
    ) }


}

fun provideStatLocalDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.statLocalDao()

fun provideTypeLocalDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.typeLocalDao()

fun providePokemonLocalDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.pokemonLocalDao()
