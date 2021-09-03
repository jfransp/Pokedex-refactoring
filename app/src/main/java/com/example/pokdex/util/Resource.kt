package com.example.pokdex.util

/*The resource class is not implemented - I had some trouble making it work with the paging3
library. It might make sense to use it on the PokemonDetail request (given the fact it
doesn't use paging), but for the sake of consistency across the code I decided not to. I'm
keeping it here in case I can figure something out in the future (same goes for the
responseHandler class).*/

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T> : Resource<T>()
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
