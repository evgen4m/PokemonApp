package com.example.pokemonapp.data.dataSource.remote

import com.example.pokemonapp.domain.entities.NamedAPIResourceList

class PokemonDataSourceImpl(private val pokemonApi: PokemonApi) : PokemonDataSource {

    override suspend fun fetchNamedAPIResourceList(offset: Int, limit: Int): NamedAPIResourceList =
        pokemonApi.fetchNamedAPIResourceList(offset = offset, limit = limit)

}