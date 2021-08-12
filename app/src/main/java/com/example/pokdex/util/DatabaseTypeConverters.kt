package com.example.pokdex.util

import androidx.room.TypeConverter
import com.example.pokdex.data.models.pokemondetails.*

class DatabaseTypeConverters {

    @TypeConverter
    fun fromStats(stats: List<Stat>) : String {
        var outputString = ""
        for (stat in stats) {
            outputString += stat.base_stat.toString() + ","
        }
        return outputString
    }

    @TypeConverter
    fun toStats(statsString: String): List<Stat> {
        val list: List<String> = statsString.split(",").map { it.trim() }

        return listOf(
            Stat(list[0].toInt()),
            Stat(list[1].toInt()),
            Stat(list[2].toInt()),
            Stat(list[3].toInt()),
            Stat(list[4].toInt()),
            Stat(list[5].toInt()),
        )
    }

    @TypeConverter
    fun fromTypes(types: List<Type>) : String {
        var outputString = ""
        for (type in types) {
            outputString += type.type.name + ","
        }
        return outputString
    }

    @TypeConverter
    fun toTypes(typesString: String): List<Type> {
        val list: List<String> = typesString.split(",").map { it.trim() }
        return listOf(
            Type(1, TypeX(list[0])),
            Type(2, TypeX(list[1]))
        )
    }

}
