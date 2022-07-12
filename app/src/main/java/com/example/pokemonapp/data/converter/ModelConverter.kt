package com.example.pokemonapp.data.converter

import com.example.pokemonapp.data.dataSource.remote.model.PokemonDetailDto
import com.example.pokemonapp.data.dataSource.remote.model.PokemonDto
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.PokemonDetail


fun PokemonDto.convertToDomain() : Pokemon {
    return Pokemon(
        name = this.name,
        url = this.url,
        weight = this.pokemonDetailDto.weight,
        height = this.pokemonDetailDto.height,
        hp = this.pokemonDetailDto.stats[0].base_stat,
        attack = this.pokemonDetailDto.stats[1].base_stat,
        defence = this.pokemonDetailDto.stats[2].base_stat,
    )
}

fun PokemonDetailDto.convertToDomain() : PokemonDetail {
    return PokemonDetail(
        name = this.name,
        weight = this.weight,
        height = this.height,
        hp = this.stats[0].base_stat,
        attack = this.stats[1].base_stat,
        defence = this.stats[2].base_stat,
        type = this.types[0].type.name
    )
}