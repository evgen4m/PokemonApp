package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.PokemonDetail
import com.example.pokemonapp.domain.entities.Result

interface PokemonRepository {

    suspend fun fetchAll(offset: Int, limit: Int) : Result<List<Pokemon>>

    suspend fun fetchPokemon(name: String) : Result<PokemonDetail>

}