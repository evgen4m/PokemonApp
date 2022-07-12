package com.example.pokemonapp.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.PokemonListItemBinding
import com.example.pokemonapp.domain.entities.Pokemon
import com.example.pokemonapp.ui.extentions.sortBy

class ListPokemonAdapter( val onItemClick: (Pokemon) -> Unit) : RecyclerView.Adapter<ListPokemonAdapter.ListViewHolder>() {

    private companion object {

        const val IMAGE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

    }

    private var context: Context? = null

    private var listPokemon = mutableListOf<Pokemon>()
        set(newValue) {
            field.addAll(newValue)
            notifyDataSetChanged()
        }

    fun setList(list: List<Pokemon>) {
        listPokemon = list as MutableList<Pokemon>
    }

    fun clearList() {
        listPokemon.clear()
    }

    fun sortList(sortParameter: String) {
        listPokemon.sortBy(sortParameter = sortParameter)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false)
        return ListViewHolder(onItemClick = onItemClick, view = view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(pokemon = listPokemon[position])
        val pokemon = listPokemon[position]

        val urlParser = pokemon.url.split("/")
        val number = Integer.parseInt(urlParser[urlParser.lastIndex - 1])

        Glide.with(context!!)
            .load("$IMAGE_URL$number.png")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.listItemBinding.pokemonImage)
    }

    override fun getItemCount(): Int =
        listPokemon.size

    inner class ListViewHolder(val onItemClick: (Pokemon) -> Unit, view: View) : RecyclerView.ViewHolder(view) {
        val listItemBinding = PokemonListItemBinding.bind(view)
        fun bind(pokemon: Pokemon) {
            listItemBinding.pokemonName.text = pokemon.name

            itemView.setOnClickListener { onItemClick(pokemon) }
        }
    }
}