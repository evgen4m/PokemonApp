package com.example.pokemonapp.data.dataSource.remote

import com.example.pokemonapp.domain.entities.NamedAPIResourceList
import com.example.pokemonapp.domain.entities.PokemonDetail

class PokemonDataSourceImpl(private val pokemonApi: PokemonApi) : PokemonDataSource {

    override suspend fun fetchNamedAPIResourceList(offset: Int, limit: Int): NamedAPIResourceList =
        pokemonApi.fetchNamedAPIResourceList(offset = offset, limit = limit)

    override suspend fun fetchPokemonDetailsByName(name: String): PokemonDetail =
        pokemonApi.fetchPokemonDetailsByName(name = name)

}