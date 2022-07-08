package com.example.pokemonapp.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.PokemonListItemBinding
import com.example.pokemonapp.domain.entities.NamedAPIResource

class ListPokemonAdapter: RecyclerView.Adapter<ListPokemonAdapter.ListViewHolder>() {

    private var context: Context? = null

    private var listPokemon: List<NamedAPIResource> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setList(list: List<NamedAPIResource>) {
        listPokemon = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false)
        return ListViewHolder(view = view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listPokemon[position])
    }


    override fun getItemCount(): Int =
        listPokemon.size

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listItemBinding = PokemonListItemBinding.bind(view)
        fun bind(namedAPIResource: NamedAPIResource) {
            listItemBinding.pokemonName.text = namedAPIResource.name
            val urlParser = namedAPIResource.url.split("/")
            val number = Integer.parseInt(urlParser[urlParser.lastIndex - 1])

            Log.d("ADAPTER", number.toString())

            Glide.with(context!!)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(listItemBinding.pokemonImage)
        }
    }

}