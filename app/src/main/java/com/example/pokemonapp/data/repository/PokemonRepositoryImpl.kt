package com.example.pokemonapp.data.repository

import com.example.pokemonapp.data.dataSource.remote.PokemonDataSource
import com.example.pokemonapp.domain.entities.NamedAPIResource
import com.example.pokemonapp.domain.entities.Result
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.*
import java.lang.Exception

class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : PokemonRepository {

    override suspend fun fetchAll(offset: Int, limit: Int): Result<List<NamedAPIResource>> = withContext(ioDispatcher) {
        supervisorScope {
            try {
                val result = async { pokemonDataSource.fetchNamedAPIResourceList(offset = offset, limit = limit).results }
                Result.Success(data = result.await())
            }catch (e: Exception) {
                e.printStackTrace()
                Result.Error(error = e)
            }
        }
    }


}