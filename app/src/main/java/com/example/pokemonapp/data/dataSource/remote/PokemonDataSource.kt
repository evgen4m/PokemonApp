package com.example.pokemonapp.data.dataSource.remote

import com.example.pokemonapp.domain.entities.NamedAPIResourceList

interface PokemonDataSource {

    suspend fun fetchNamedAPIResourceList(offset: Int, limit: Int) : NamedAPIResourceList

}