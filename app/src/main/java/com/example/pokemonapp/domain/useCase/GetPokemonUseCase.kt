package com.example.pokemonapp.domain.useCase

import com.example.pokemonapp.domain.repository.PokemonRepository

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun invoke(offset: Int, limit: Int) =
        pokemonRepository.fetchAll(offset = offset, limit = limit)

}