package com.example.pokdex.data.models.pokemonlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val url: String
) : Parcelable
