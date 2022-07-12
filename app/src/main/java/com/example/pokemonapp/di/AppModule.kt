package com.example.pokemonapp.di

import com.example.pokemonapp.presentation.detail.PokemonDetailViewModel
import com.example.pokemonapp.presentation.list.ListPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<ListPokemonViewModel> {
        ListPokemonViewModel(
            getPokemonUseCase = get()
        )
    }

    viewModel<PokemonDetailViewModel> {
        PokemonDetailViewModel(
            getPokemonDetailByName = get()
        )
    }

}