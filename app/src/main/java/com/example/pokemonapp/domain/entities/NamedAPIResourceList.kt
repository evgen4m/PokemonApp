package com.example.pokemonapp.domain.entities

data class NamedAPIResourceList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Pokemon>
)