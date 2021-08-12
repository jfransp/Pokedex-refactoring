package com.example.pokdex.util

import androidx.room.TypeConverter
import com.example.pokdex.data.models.pokemondetails.*
import com.google.gson.Gson

class DatabaseTypeConverter {

    @TypeConverter
    fun fromStats(stats: List<Stat>) : String {
        return Gson().toJson(stats)
    }

    @TypeConverter
    fun toStats(jsonString: String): List<Stat> {
        return Gson().fromJson(jsonString, Array<Stat>::class.java).toList()
    }

    @TypeConverter
    fun fromTypes(types: List<Type>) : String {
        return Gson().toJson(types)
    }

    @TypeConverter
    fun toTypes(jsonString: String): List<Type> {
        return Gson().fromJson(jsonString, Array<Type>::class.java).toList()
    }

}
