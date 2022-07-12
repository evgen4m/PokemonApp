package com.example.pokemonapp.data.dataSource.remote.model


data class PokemonDto(
    val name: String,
    val url: String,
    val pokemonDetailDto: PokemonDetailDto
)
