package com.example.pokemonapp.domain.useCase

import com.example.pokemonapp.domain.repository.PokemonRepository

class GetPokemonDetailByNameUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun invoke(name : String) = pokemonRepository.fetchPokemon(name = name)

}