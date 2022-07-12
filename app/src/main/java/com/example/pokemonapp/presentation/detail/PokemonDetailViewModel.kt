package com.example.pokemonapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.entities.PokemonDetail
import com.example.pokemonapp.domain.entities.Result
import com.example.pokemonapp.domain.useCase.GetPokemonDetailByNameUseCase
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonDetailByName: GetPokemonDetailByNameUseCase
) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail: LiveData<PokemonDetail> = _pokemonDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPokemon(name : String) {
        viewModelScope.launch {
            val result = getPokemonDetailByName.invoke(name = name)
            handleResult(result = result)
        }
    }

    private fun handleResult(result: Result<PokemonDetail>) {
        when(result) {
            is Result.Success -> { _pokemonDetail.value = result.data!! }
            is Result.Error -> { _error.value = result.error.toString() }
        }
    }

}