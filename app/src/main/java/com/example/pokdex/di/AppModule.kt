package com.example.pokdex.di

import android.app.Application
import androidx.room.Room
import com.example.pokdex.data.localdb.SavedPokemonsDatabase
import com.example.pokdex.data.remoteAPI.PokeApi
import com.example.pokdex.data.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokeApi
    ) = PokemonRepository(api)

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app,SavedPokemonsDatabase::class.java, "saved_pokemon_data")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(db: SavedPokemonsDatabase) = db.getDao()

}
