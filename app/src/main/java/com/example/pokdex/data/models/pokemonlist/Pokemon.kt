package com.example.pokdex.data.models.pokemonlist

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokdex.util.getImageUrlFromUrl
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val url: String
) : Parcelable