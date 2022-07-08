package com.example.pokemonapp.di

import com.example.pokemonapp.presentation.ListPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<ListPokemonViewModel> {
        ListPokemonViewModel(
            getPokemonUseCase = get()
        )
    }

}