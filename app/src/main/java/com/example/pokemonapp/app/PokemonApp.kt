package com.example.pokemonapp.app

import android.app.Application
import com.example.pokemonapp.di.appModule
import com.example.pokemonapp.di.dataModule
import com.example.pokemonapp.di.domainModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PokemonApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.ERROR)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }

}