package com.example.pokdex.util

import androidx.room.TypeConverter
import com.example.pokdex.data.models.pokemondetails.*
import com.google.gson.Gson

class DatabaseTypeConverter {

    @TypeConverter
    fun fromSprites(sprites: Sprites): String {
        return sprites.other.dream_world.front_default
    }

    /*@TypeConverter
    fun toSprites(url: String): Sprites {
        return Sprites(Other(DreamWorld(url)))
    }*/

    @TypeConverter
    fun fromStats(stats: List<Stat>) : String {
        return Gson().toJson(stats)
    }

    @TypeConverter
    fun toStats(jsonString: String): List<Stat> {
        return Gson().fromJson(jsonString, Array<Stat>::class.java).toList()
    }

}
