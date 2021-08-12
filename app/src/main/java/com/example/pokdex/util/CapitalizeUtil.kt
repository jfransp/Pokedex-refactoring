package com.example.pokdex.util

//The standard "capitalize()" function seems to be deprecated for some reason and I don't know
//what they replaced it with, so I'm just gonna set up this as not to use a deprecated function.
fun String.capitalizeUtil () : String {
    if (this.isNotEmpty()) {
        return substring(0, 1).uppercase() + substring(1).lowercase()
    }
    return this
}