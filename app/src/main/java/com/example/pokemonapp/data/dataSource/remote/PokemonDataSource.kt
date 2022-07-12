package com.example.pokemonapp.data.dataSource.remote

import com.example.pokemonapp.data.dataSource.remote.model.NamedAPIResourceList
import com.example.pokemonapp.data.dataSource.remote.model.PokemonDetailDto

interface PokemonDataSource {

    suspend fun fetchNamedAPIResourceList(offset: Int, limit: Int) : NamedAPIResourceList

    suspend fun fetchPokemonDetailsByName(name: String) : PokemonDetailDto

}