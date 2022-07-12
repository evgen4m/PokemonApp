package com.example.pokemonapp.data.dataSource.remote.model

data class NamedAPIResourceList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonDto>
)