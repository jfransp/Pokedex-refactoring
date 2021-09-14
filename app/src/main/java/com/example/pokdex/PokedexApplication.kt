package com.example.pokdex

import android.app.Application
import com.example.pokdex.di.appModule
import com.example.pokdex.di.networkModule
import com.example.pokdex.di.useCasesModule
import com.example.pokdex.di.viewModelModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class PokedexApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PokedexApplication)

            modules(
                appModule,
                networkModule,
                useCasesModule,
                viewModelModule
            )
        }
    }

}
