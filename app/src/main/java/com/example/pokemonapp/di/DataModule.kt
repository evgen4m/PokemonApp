package com.example.pokemonapp.di

import com.example.pokemonapp.data.dataSource.remote.PokemonApi
import com.example.pokemonapp.data.dataSource.remote.PokemonDataSource
import com.example.pokemonapp.data.dataSource.remote.PokemonDataSourceImpl
import com.example.pokemonapp.data.repository.PokemonRepositoryImpl
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://pokeapi.co/api/v2/"

val dataModule = module {

    single<PokemonRepository> {
        PokemonRepositoryImpl(
            pokemonDataSource = get(),
            ioDispatcher = get()
        )
    }

    single<PokemonDataSource> {
        PokemonDataSourceImpl(
            pokemonApi = get()
        )
    }

    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideIoDispatcher() }
    factory { provideForecastApi(get()) }

}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()


private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()


private fun provideForecastApi(retrofit: Retrofit): PokemonApi =
    retrofit.create(PokemonApi::class.java)

private fun provideIoDispatcher() : CoroutineDispatcher = Dispatchers.IO