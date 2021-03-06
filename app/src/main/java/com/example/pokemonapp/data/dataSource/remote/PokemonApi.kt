package com.example.pokemonapp.data.dataSource.remote

import com.example.pokemonapp.data.dataSource.remote.model.NamedAPIResourceList
import com.example.pokemonapp.data.dataSource.remote.model.PokemonDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun fetchNamedAPIResourceList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ) : NamedAPIResourceList


    @GET("pokemon/{pokemonName}")
    suspend fun fetchPokemonDetailsByName(
        @Path("pokemonName") name: String
    ) : PokemonDetailDto

}