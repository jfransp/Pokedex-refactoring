package com.example.data

import com.example.data.database.daos.PokemonLocalDao
import com.example.data.database.daos.StatLocalDao
import com.example.data.database.daos.TypeLocalDao
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.mappers.MapperImpl

class LocalDataSource(
    private val pokeDao: PokemonLocalDao,
    private val statDao: StatLocalDao,
    private val typeDao: TypeLocalDao,
    private val mapper: MapperImpl
) {

    suspend fun getPokemon(pokemonId: Int): PokemonDetails =
        mapper.mapPokemonLocalToPokemonDetails(
            pokemon = pokeDao.getPokemonLocal(pokemonId),
            stats = statDao.getStatsLocal(pokemonId),
            types = typeDao.getTypesLocal(pokemonId)
        )

    suspend fun insertPokemon(pokemon: PokemonDetails) =
        mapper.mapPokemonDetailsToPokemonLocal(pokemon).let {
            pokeDao.insertPokemonLocal(it.first)
            for (stat in it.second) {
                statDao.insertStatLocal(stat)
            }
            for (type in it.third) {
                typeDao.insertTypeLocal(type)
            }
        }

    suspend fun deletePokemon(pokemonId: Int) =
        pokemonId.let {
            pokeDao.deletePokemonLocal(it)
            statDao.deleteStatLocal(it)
            typeDao.deleteTypeLocal(it)
        }

}
