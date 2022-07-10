package com.example.pokemonapp.presentation

sealed class ListPokemonState {

    data class Loading(val load: Boolean): ListPokemonState()

}
