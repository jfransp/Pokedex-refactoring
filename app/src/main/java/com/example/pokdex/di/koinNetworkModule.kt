package com.example.pokdex.di

import com.example.data.remoteAPI.PokeApi
import com.example.pokdex.util.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { provideRetrofit() }
    single { providePokeApi(get()) }

}

fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun providePokeApi(retrofit: Retrofit): PokeApi =
    retrofit.create(PokeApi::class.java)
