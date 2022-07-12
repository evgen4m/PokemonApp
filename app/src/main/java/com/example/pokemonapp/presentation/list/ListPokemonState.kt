package com.example.pokemonapp.presentation.list

sealed class ListPokemonState {

    data class Loading(val load: Boolean): ListPokemonState()

}
