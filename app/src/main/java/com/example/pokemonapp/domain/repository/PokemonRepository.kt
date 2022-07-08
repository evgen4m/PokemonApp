package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.domain.entities.NamedAPIResource
import com.example.pokemonapp.domain.entities.Result

interface PokemonRepository {

    suspend fun fetchAll(offset: Int, limit: Int) : Result<List<NamedAPIResource>>

}