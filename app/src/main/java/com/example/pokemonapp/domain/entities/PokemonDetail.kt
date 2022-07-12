package com.example.pokemonapp.domain.entities

data class PokemonDetail(
    val name: String,
    val height: Int,
    val weight: Int,
    val attack: Int,
    val defence: Int,
    val hp: Int,
    val type: String
)
