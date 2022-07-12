package com.example.pokemonapp.data.dataSource.remote

import com.example.pokemonapp.data.dataSource.remote.model.NamedAPIResourceList
import com.example.pokemonapp.data.dataSource.remote.model.PokemonDetailDto

class PokemonDataSourceImpl(private val pokemonApi: PokemonApi) : PokemonDataSource {

    override suspend fun fetchNamedAPIResourceList(offset: Int, limit: Int): NamedAPIResourceList =
        pokemonApi.fetchNamedAPIResourceList(offset = offset, limit = limit)

    override suspend fun fetchPokemonDetailsByName(name: String): PokemonDetailDto =
        pokemonApi.fetchPokemonDetailsByName(name = name)

}