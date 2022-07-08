package com.example.pokemonapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.entities.NamedAPIResource
import com.example.pokemonapp.domain.entities.Result
import com.example.pokemonapp.domain.useCase.GetPokemonUseCase
import kotlinx.coroutines.launch

class ListPokemonViewModel(
    private val getPokemonUseCase: GetPokemonUseCase
): ViewModel() {

    private val _listPokemon = MutableLiveData<List<NamedAPIResource>>()
    val listPokemon: LiveData<List<NamedAPIResource>> = _listPokemon

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadPokemon() {
        viewModelScope.launch {
            val result = getPokemonUseCase.invoke(0,30)
            handleResult(result = result)
        }
    }

    private fun handleResult(result: Result<List<NamedAPIResource>>) {
        when(result) {
            is Result.Success -> {
                _listPokemon.value = result.data
            }
            is Result.Error -> {
                _error.value = result.error.toString()
            }
        }
    }
}