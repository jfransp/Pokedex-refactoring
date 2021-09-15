package com.example.pokdex.di

import com.example.data.remoteAPI.KtorHttpClient.AppClient
import com.example.data.remoteAPI.KtorHttpClient.AppClientImpl
import com.example.data.remoteAPI.service.PokeApi
import com.example.data.remoteAPI.service.PokeApiImpl
import org.koin.dsl.module

val networkModule = module {

    single { AppClientImpl() as AppClient }
    single { PokeApiImpl(get()) as PokeApi }

}
