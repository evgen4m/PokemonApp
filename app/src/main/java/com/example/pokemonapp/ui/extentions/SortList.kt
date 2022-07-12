package com.example.pokemonapp.ui.extentions

import com.example.pokemonapp.domain.entities.Pokemon
import java.util.*

fun List<Pokemon>.sortBy(sortParameter: String) {

    Collections.sort(this) { pokemon0, pokemon1 ->
        return@sort when(sortParameter) {
            "attack" -> pokemon1.attack - pokemon0.attack
            "defense" -> pokemon1.defence - pokemon0.defence
            "hp" -> pokemon1.hp - pokemon0.hp
            else -> 0
        }
    }

}