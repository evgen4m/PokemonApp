package com.example.pokemonapp.data.dataSource.remote

import com.example.pokemonapp.domain.entities.NamedAPIResourceList
import com.example.pokemonapp.domain.entities.PokemonDetail

interface PokemonDataSource {

    suspend fun fetchNamedAPIResourceList(offset: Int, limit: Int) : NamedAPIResourceList

    suspend fun fetchPokemonDetailsByName(name: String) : PokemonDetail

}