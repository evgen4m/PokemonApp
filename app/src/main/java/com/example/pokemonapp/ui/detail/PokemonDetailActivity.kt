package com.example.pokemonapp.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityPokemonDetailBinding
import com.example.pokemonapp.domain.entities.PokemonDetail
import com.example.pokemonapp.presentation.detail.PokemonDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_NAME = "EXTRA_NAME"

        fun start(context: Context, name: String) {
            val intent = Intent(context, PokemonDetailActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
            }
            context.startActivity(intent)
        }

    }

    private val viewModel: PokemonDetailViewModel by viewModel()

    private lateinit var binding: ActivityPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra(EXTRA_NAME)?.let { name -> viewModel.fetchPokemon(name = name) }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.pokemonDetail.observe(this, ::updateUiByPokemon)
    }

    private fun updateUiByPokemon(pokemonDetail: PokemonDetail) {
        binding.pokemonDetailName.text = getString(R.string.name, pokemonDetail.name)
        binding.pokemonDetailHeight.text =
            getString(R.string.height, pokemonDetail.height.toString())
        binding.pokemonDetailWeight.text =
            getString(R.string.weight, pokemonDetail.weight.toString())

        pokemonDetail.types.forEach { types ->
            binding.pokemonDetailType.text = getString(R.string.type, types.type.name)
        }

        pokemonDetail.stats.forEach { stats ->
            when (stats.stat.name) {
                getString(R.string.text_attack) -> binding.pokemonDetailAttack.text =
                    getString(R.string.attack, stats.base_stat.toString())
                getString(R.string.text_defense) -> binding.pokemonDetailDefense.text =
                    getString(R.string.defense, stats.base_stat.toString())
                getString(R.string.text_hp) -> binding.pokemonDetailHP.text =
                    getString(R.string.hp, stats.base_stat.toString())
            }
        }
    }
}