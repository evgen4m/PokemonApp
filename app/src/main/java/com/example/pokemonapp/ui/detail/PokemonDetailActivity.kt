package com.example.pokemonapp.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent.getStringExtra(EXTRA_NAME)?.let { name -> viewModel.fetchPokemon(name = name) }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.pokemonDetail.observe(this, ::updateUiByPokemon)
        viewModel.error.observe(this, ::showErrorMessage)
    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateUiByPokemon(pokemonDetail: PokemonDetail) {
        binding.pokemonDetailName.text = getString(R.string.name, pokemonDetail.name)
        binding.pokemonDetailHeight.text =
            getString(R.string.height, pokemonDetail.height.toString())
        binding.pokemonDetailWeight.text =
            getString(R.string.weight, pokemonDetail.weight.toString())
        binding.pokemonDetailType.text = getString(R.string.type, pokemonDetail.type)
        binding.pokemonDetailAttack.text =
            getString(R.string.attack, pokemonDetail.attack.toString())
        binding.pokemonDetailDefense.text =
            getString(R.string.defense, pokemonDetail.defence.toString())
        binding.pokemonDetailHP.text = getString(R.string.hp, pokemonDetail.hp.toString())

    }
}