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

class MapperImpl: Mapper {

    override fun mapPokemonDetailsToPokemonLocal(pokemon: PokemonDetails): Triple<PokemonLocal, List<StatLocal>, List<TypeLocal>> {

        val pokemonLocal = PokemonLocal(
            height = pokemon.height,
            id = pokemon.id,
            name = pokemon.name,
            weight = pokemon.weight
        )

        val statsLocal = mutableListOf<StatLocal>()
        for (stat in pokemon.stats) {
            statsLocal.add(mapStatToStatLocal(stat, pokemon.id))
        }

        val typesLocal = mutableListOf<TypeLocal>()
        for (type in pokemon.types) {
            typesLocal.add(mapTypeToTypeLocal(type, pokemon.id))
        }

        return Triple(
            first = pokemonLocal,
            second = statsLocal,
            third = typesLocal
        )

    }

    override fun mapPokemonLocalToPokemonDetails(
        pokemon: PokemonLocal,
        stats: List<StatLocal>,
        types: List<TypeLocal>
    ): PokemonDetails {

        val outputStatList = mutableListOf<Stat>()
        for (stat in stats) {
            outputStatList.add(mapStatLocalToStat(stat))
        }

        val outputTypeList = mutableListOf<Type>()
        for (type in types) {
            outputTypeList.add(mapTypeLocalToType(type))
        }

        return PokemonDetails(
            height = pokemon.height,
            id = pokemon.id,
            name = pokemon.name,
            stats = outputStatList,
            types = outputTypeList,
            weight = pokemon.weight
        )
    }

    override fun mapPokemonListRemoteToPokemonList(pokemonList: PokemonListRemote): PokemonList =
        PokemonList(
            count = pokemonList.count,
            next = pokemonList.next,
            preview = pokemonList.preview,
            results = pokemonList.results
        )

    override fun mapPokemonDetailsRemoteToPokemonDetails(pokemonDetails: PokemonDetailsRemote): PokemonDetails {

        val outputStatList = mutableListOf<Stat>()
        for (stat in pokemonDetails.stats) {
            outputStatList.add(mapStatRemoteToStat(stat))
        }

        val outputTypeList = mutableListOf<Type>()
        for (type in pokemonDetails.types) {
            outputTypeList.add(mapTypeRemoteToType(type))
        }

        return PokemonDetails(
            height = pokemonDetails.height,
            id = pokemonDetails.id,
            name = pokemonDetails.name,
            stats = outputStatList,
            types = outputTypeList,
            weight = pokemonDetails.weight
        )
    }

    override fun mapStatRemoteToStat(stat: StatRemote): Stat {
        return Stat(
            base_stat = stat.base_stat
        )
    }

    override fun mapTypeRemoteToType(type: TypeRemote): Type {
        return Type(
            slot = type.slot,
            type = mapTypeXRemoteToTypeX(type.type)
        )
    }

    override fun mapPokemonRemoteToPokemon(pokemon: PokemonRemote): Pokemon {
        return Pokemon(
            name = pokemon.name,
            url = pokemon.url
        )
    }

    override fun mapTypeXRemoteToTypeX(typex: TypeXRemote): TypeX {
        return TypeX(
            name = typex.name
        )
    }

    override fun mapStatLocalToStat(stat: StatLocal): Stat =
        Stat(
            base_stat = stat.base_stat
        )

    override fun mapTypeLocalToType(type: TypeLocal): Type =
        Type(
            slot = type.slot,
            type = TypeX(type.type)
        )

    override fun mapStatToStatLocal(stat: Stat, pokemonId: Int): StatLocal =
        StatLocal(
            base_stat = stat.base_stat,
            poke_id = pokemonId,
        )

    override fun mapTypeToTypeLocal(type: Type, pokemonId: Int): TypeLocal =
        TypeLocal(
            slot = type.slot,
            type = type.type.name,
            poke_id = pokemonId,

        )
}
