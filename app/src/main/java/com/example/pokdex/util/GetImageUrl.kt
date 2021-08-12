package com.example.pokdex.util

fun getImageUrlFromUrl(url: String): String {

    val pokeId = url
        .replace("https://pokeapi.co/api/v2/pokemon/", "")
        .replace("/", "")

    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokeId}.png"

}
