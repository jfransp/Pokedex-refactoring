package com.example.pokdex.di

import android.app.Application
import androidx.room.Room
import com.example.data.LocalDataSource
import com.example.data.PokemonRepositoryImpl
import com.example.data.RemoteDataSource
import com.example.data.database.daos.PokemonLocalDao
import com.example.data.database.daos.StatLocalDao
import com.example.data.database.daos.TypeLocalDao
import com.example.data.database.db.PokemonDatabase
import com.example.data.remoteAPI.PokeApi
import com.example.domain.repositories.PokemonRepository
import com.example.domain.util.ErrorHandler
import com.example.mappers.Mapper
import com.example.mappers.MapperImpl
import com.example.util.ErrorHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    //Repository
//    @Singleton
//    @Provides
//    fun providePokemonRepository(
//        remoteDataSource: RemoteDataSource,
//        localDataSource: LocalDataSource
//    ) = PokemonRepositoryImpl(remoteDataSource, localDataSource) as PokemonRepository
//
//    //DataSources
//    @Provides
//    @Singleton
//    fun provideRemoteDataSource(
//        api: PokeApi,
//        mapper: Mapper
//    ) = RemoteDataSource(api, mapper)
//
//    @Provides
//    @Singleton
//    fun provideLocalDataSource(
//        pokeDao: PokemonLocalDao,
//        statDao: StatLocalDao,
//        typeDao: TypeLocalDao,
//        mapper: Mapper
//    ) = LocalDataSource(pokeDao, statDao, typeDao, mapper)
//
//    //ErrorHandler
//    @Provides
//    @Singleton
//    fun provideErrorHandler() = ErrorHandlerImpl() as ErrorHandler
//
//    //Mapper
//    @Provides
//    @Singleton
//    fun provideMapper() = MapperImpl() as Mapper
//
//    //DataBase
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        app: Application
//    ) = Room.databaseBuilder(app, PokemonDatabase::class.java, "saved_pokemon_data")
//        .fallbackToDestructiveMigration()
//        .build()
//
//    //Daos
//    @Singleton
//    @Provides
//    fun providePokemonLocalDao(pokemonDataBase: PokemonDatabase) = pokemonDataBase.pokemonLocalDao()
//
//    @Singleton
//    @Provides
//    fun provideStatLocalDao(pokemonDataBase: PokemonDatabase) = pokemonDataBase.statLocalDao()
//
//    @Singleton
//    @Provides
//    fun provideTypeLocalDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.typeLocalDao()
//
//}
