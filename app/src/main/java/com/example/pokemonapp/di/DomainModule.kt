package com.example.pokemonapp.di

import com.example.pokemonapp.domain.useCase.GetPokemonUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetPokemonUseCase> {
        GetPokemonUseCase(pokemonRepository = get())
    }

}