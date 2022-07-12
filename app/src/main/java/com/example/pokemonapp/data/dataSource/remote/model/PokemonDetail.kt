package com.example.pokemonapp.data.dataSource.remote.model

data class PokemonDetailDto(
    val abilities: List<Abilities>,
    val height: Int,
    val name: String,
    val stats: List<Stats>,
    val types: List<Types>,
    val weight: Int
)

data class Abilities(
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
)

data class Ability(
    val name : String,
    val url : String
)

data class Types(
    val slot: Int,
    val type: Type
)

data class Type(
    val name : String,
    val url: String
)

data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
)

data class Stat(
    val name : String,
    val url : String
)