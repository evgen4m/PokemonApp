package com.example.pokemonapp.ui.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.presentation.list.ListPokemonState
import com.example.pokemonapp.presentation.list.ListPokemonViewModel
import com.example.pokemonapp.ui.detail.PokemonDetailActivity
import com.example.pokemonapp.ui.extentions.sortBy
import okhttp3.internal.notify
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPokemonActivity : AppCompatActivity() {

    private val viewModel by viewModel<ListPokemonViewModel>()

    private lateinit var binding: ActivityMainBinding

    private var adapter = ListPokemonAdapter { pokemon ->
        openDetailScreen(name = pokemon.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        initViews()

    }

    private fun initViews() {
        binding.listPokemon.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        binding.listPokemon.layoutManager = layoutManager
        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.loadMorePokemon()
            }
        })

        binding.updateListPokemon.setOnClickListener {
            adapter.clearList()
            viewModel.loadRandomPokemon()
        }

        binding.sortAttack.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                adapter.sortList(sortParameter = getString(R.string.text_attack))
            }
        }

        binding.sortDefense.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                adapter.sortList(sortParameter = getString(R.string.text_defense))
            }
        }

        binding.sortHp.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                adapter.sortList(sortParameter = getString(R.string.text_hp))
            }
        }
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

    private fun openDetailScreen(name: String) {
        PokemonDetailActivity.start(this, name = name)
    }
}