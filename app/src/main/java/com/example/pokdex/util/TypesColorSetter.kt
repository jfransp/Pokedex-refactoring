package com.example.pokdex.util

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.DrawableCompat
import com.example.domain.models.pokemondetails.Type
import com.example.pokdex.R
import com.example.pokdex.databinding.FragmentPokemonDetailsBinding
import com.example.pokdex.databinding.SavedPokemonItemBinding

//I tried to make it work using only one function, but I couldn't figure out how to pass an argument
//that might take two different types (FragmentPokemonDetailsBinding or SavedPokemonItemBinding) without
//getting errors on the binding classes, neither could I think of a different solution.

@RequiresApi(Build.VERSION_CODES.M)
fun fragmentTypesColorSetter(
    slot: Int,
    context: Context,
    type: Type,
    binding: FragmentPokemonDetailsBinding
) {

    val bindingReference = when (slot) {
        1 -> binding.typeSlotOne.background
        2 -> binding.typeSlotTwo.background
        else -> binding.typeSlotOne.background
    }

    when (type.type.name) {
        "normal" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.normal_color))
        "fire" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.fire_color))
        "water" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.water_color))
        "grass" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.grass_color))
        "electric" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.electric_color))
        "ice" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.ice_color))
        "fighting" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.fighting_color))
        "poison" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.poison_color))
        "ground" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.ground_color))
        "flying" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.flying_color))
        "psychic" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.psychic_color))
        "bug" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.bug_color))
        "rock" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.rock_color))
        "ghost" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.ghost_color))
        "dark" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.dark_color))
        "dragon" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.dragon_color))
        "steel" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.steel_color))
        "fairy" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.fairy_color))
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun savedPokemonItemColorSetter(
    slot: Int,
    context: Context,
    type: Type,
    binding: SavedPokemonItemBinding
) {

    val bindingReference = when (slot) {
        1 -> binding.typeSlotOne.background
        2 -> binding.typeSlotTwo.background
        else -> binding.typeSlotOne.background
    }

    when (type.type.name) {
        "normal" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.normal_color))
        "fire" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.fire_color))
        "water" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.water_color))
        "grass" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.grass_color))
        "electric" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.electric_color))
        "ice" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.ice_color))
        "fighting" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.fighting_color))
        "poison" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.poison_color))
        "ground" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.ground_color))
        "flying" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.flying_color))
        "psychic" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.psychic_color))
        "bug" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.bug_color))
        "rock" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.rock_color))
        "ghost" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.ghost_color))
        "dark" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.dark_color))
        "dragon" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.dragon_color))
        "steel" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.steel_color))
        "fairy" -> DrawableCompat.setTint(bindingReference, context.getColor(R.color.fairy_color))
    }
}
