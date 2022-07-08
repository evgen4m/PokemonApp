package com.example.pokemonapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.domain.entities.NamedAPIResource
import com.example.pokemonapp.presentation.ListPokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPokemonActivity : AppCompatActivity() {

    private val viewModel by viewModel<ListPokemonViewModel>()

    private lateinit var binding: ActivityMainBinding

    private var adapter = ListPokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()

        binding.listPokemon.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.listPokemon.observe(this, ::setListPokemon)
        viewModel.error.observe(this, ::showErrors)
    }

    private fun showErrors(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun setListPokemon(list: List<NamedAPIResource>) {
        adapter.setList(list = list)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPokemon()
    }
}