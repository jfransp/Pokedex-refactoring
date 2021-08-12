package com.example.pokdex.util

fun String.capitalizeUtil () : String {
    if (this.isNotEmpty()) {
        return substring(0, 1).uppercase() + substring(1).lowercase()
    }
    return this
}