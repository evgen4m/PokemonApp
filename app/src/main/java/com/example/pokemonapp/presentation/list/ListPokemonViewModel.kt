package com.example.pokemonapp.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.domain.entities.Result
import com.example.pokemonapp.domain.useCase.GetPokemonUseCase
import kotlinx.coroutines.launch

class ListPokemonViewModel(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private companion object {
        var OFFSET = 0
        const val LIMIT = 30
        const val MAX_LIMIT = 777
    }

    private val _listPokemon = MutableLiveData<List<Pokemon>>()
    val listPokemon: LiveData<List<Pokemon>> = _listPokemon

    private val _listPokemonState = MutableLiveData<ListPokemonState>()
    val listPokemonState: LiveData<ListPokemonState> = _listPokemonState

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        loadData(offset = OFFSET, limit = LIMIT)
    }

    fun loadMorePokemon() {
        _listPokemonState.value = ListPokemonState.Loading(load = true)
        OFFSET += 30
        loadData(offset = OFFSET, limit = LIMIT)
    }

    fun loadRandomPokemon() {
        val randomOffset = (0..MAX_LIMIT).random()
        loadData(offset = randomOffset, limit = LIMIT)
    }

    private fun loadData(offset: Int, limit: Int) {
        _listPokemonState.value = ListPokemonState.Loading(load = true)
        viewModelScope.launch {
            val result = getPokemonUseCase.invoke(offset = offset, limit = limit)
            handleResult(result = result)
        }
    }

    private fun handleResult(result: Result<List<Pokemon>>) {
        when (result) {
            is Result.Success -> {
                _listPokemonState.value = ListPokemonState.Loading(load = false)
                _listPokemon.value = result.data
            }
            is Result.Error -> {
                _error.value = result.error.toString()
            }
        }
    }
}