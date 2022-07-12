package com.example.pokemonapp.data.repository

import com.example.pokemonapp.data.converter.convertToDomain
import com.example.pokemonapp.data.dataSource.remote.PokemonDataSource
import com.example.pokemonapp.data.dataSource.remote.model.PokemonDetailDto
import com.example.pokemonapp.data.dataSource.remote.model.PokemonDto
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.PokemonDetail
import com.example.pokemonapp.domain.entities.Result
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.*
import kotlin.Exception

class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : PokemonRepository {

    private companion object {

        const val ITEM_LIMIT = 30

    }

    override suspend fun fetchAll(offset: Int): Result<List<Pokemon>> = withContext(ioDispatcher) {
        return@withContext try {
            val list = arrayListOf<Pokemon>()
            val resultList = async { pokemonDataSource.fetchNamedAPIResourceList(offset = offset, limit = ITEM_LIMIT) }
            resultList.await().results.forEach { pokemon ->
                val resultDetail = pokemonDataSource.fetchPokemonDetailsByName(name = pokemon.name)
                list.add(
                    PokemonDto(
                        name = pokemon.name,
                        url = pokemon.url,
                        pokemonDetailDto = resultDetail
                    ).convertToDomain()
                )
                println(list)
            }
            Result.Success(data = list)
        }catch (e: Exception) {
            e.printStackTrace()
            Result.Error(error = e)
        }
    }

    override suspend fun fetchPokemon(name: String): Result<PokemonDetail> = withContext(ioDispatcher) {
        return@withContext try {
            val result = pokemonDataSource.fetchPokemonDetailsByName(name = name)
            Result.Success(data = result.convertToDomain())
        }catch (e: Exception) {
            e.printStackTrace()
            Result.Error(error = e)
        }
    }
}