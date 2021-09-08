package com.example.mappers

import com.example.data.database.entities.PokemonLocal
import com.example.data.database.entities.StatLocal
import com.example.data.database.entities.TypeLocal
import com.example.data.remoteAPI.models.*
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.models.pokemondetails.Stat
import com.example.domain.models.pokemondetails.Type
import com.example.domain.models.pokemondetails.TypeX
import com.example.domain.models.pokemonlist.Pokemon
import com.example.domain.models.pokemonlist.PokemonList

interface Mapper {

    fun mapPokemonDetailsToPokemonLocal(pokemon: PokemonDetails): Triple<PokemonLocal, List<StatLocal>, List<TypeLocal>>

    fun mapPokemonLocalToPokemonDetails(
        pokemon: PokemonLocal,
        stats: List<StatLocal>,
        types: List<TypeLocal>
    ): PokemonDetails

    fun mapPokemonListRemoteToPokemonList(pokemonList: PokemonListRemote): PokemonList

    fun mapPokemonDetailsRemoteToPokemonDetails(pokemonDetails: PokemonDetailsRemote): PokemonDetails

    fun mapStatLocalToStat(stat: StatLocal): Stat

    fun mapTypeLocalToType(type: TypeLocal): Type

    fun mapStatToStatLocal(stat: Stat, pokemonId: Int): StatLocal

    fun mapTypeToTypeLocal(type: Type, pokemonId: Int): TypeLocal

    fun mapTypeRemoteToType(type: TypeRemote): Type

    fun mapStatRemoteToStat(stat: StatRemote): Stat

    fun mapPokemonRemoteToPokemon(pokemon: PokemonRemote): Pokemon

    fun mapTypeXRemoteToTypeX(typex: TypeXRemote): TypeX

}
