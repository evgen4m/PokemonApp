package com.example.pokemonapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.presentation.ListPokemonState
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
        initViews()
    }

    private fun initViews() {
        binding.listPokemon.adapter = adapter
        val layoutManager = GridLayoutManager(this, 3)
        binding.listPokemon.layoutManager = layoutManager
        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.loadMorePokemon()
            }
        })
    }

    private fun observeViewModel() {
        viewModel.listPokemon.observe(this, ::setListPokemon)
        viewModel.error.observe(this, ::showErrors)
        viewModel.listPokemonState.observe(this, ::showLoading)
    }

    private fun showLoading(listPokemonState: ListPokemonState) {
        when(listPokemonState) {
            is ListPokemonState.Loading -> {
                showProgressBar(load = listPokemonState.load)
            }
        }
    }

    private fun showProgressBar(load: Boolean) {
        binding.pbProgress.isVisible = load
    }

    private fun showErrors(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun setListPokemon(list: List<Pokemon>) {
        adapter.setList(list = list)
    }
}