package com.example.mappers

import com.example.data.database.entities.PokemonLocal
import com.example.data.database.entities.StatLocal
import com.example.data.database.entities.TypeLocal
import com.example.domain.models.pokemondetails.PokemonDetails

//Só uma ideia básica de como implementar um mapper de forma mais concisa - eu sei que o ideal é
//usar interfaces aqui também, mas ainda não entendi direito e deixei para tentar implementar depois.

fun remoteToLocal(pokemon: PokemonDetails): Triple<PokemonLocal, List<StatLocal>, List<TypeLocal>> {

    val pokemonLocal = PokemonLocal(
        height = pokemon.height,
        id = pokemon.id,
        name = pokemon.name,
        weight = pokemon.weight
    )

    val statsLocal = mutableListOf<StatLocal>()
    for (stat in pokemon.stats) {
        val newStat = StatLocal(
            base_stat = stat.base_stat,
            poke_id = pokemon.id,
        )
        statsLocal.add(newStat)
    }

    val typesLocal = mutableListOf<TypeLocal>()
    for (type in pokemon.types) {
        val newType = TypeLocal(
            slot = type.slot,
            type = type.type.name,
        )
        typesLocal.add(newType)
    }

    return Triple(
        first = pokemonLocal,
        second = statsLocal,
        third = typesLocal
    )

}
