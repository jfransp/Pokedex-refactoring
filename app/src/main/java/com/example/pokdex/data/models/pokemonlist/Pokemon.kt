package com.example.pokdex.data.models.pokemonlist

import android.os.Parcelable
import com.example.pokdex.util.getImageUrlFromUrl
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val url: String
) : Parcelable {
    val imageUrl = getImageUrlFromUrl(url)
}