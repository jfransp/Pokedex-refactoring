package com.example.data

import com.example.data.database.daos.PokemonLocalDao
import com.example.data.database.daos.StatLocalDao
import com.example.data.database.daos.TypeLocalDao
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.mappers.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class LocalDataSource(
    private val pokeDao: PokemonLocalDao,
    private val statDao: StatLocalDao,
    private val typeDao: TypeLocalDao,
    private val mapper: Mapper
) {

    suspend fun getPokemonDetails(pokemonName: String): PokemonDetails? =
        pokeDao.getPokemonLocal(pokemonName)?.let {
            mapper.mapPokemonLocalToPokemonDetails(
                pokemon = it,
                stats = statDao.getStatsLocal(pokemonName),
                types = typeDao.getTypesLocal(pokemonName)
            )
        }

    fun getPokemonDetailsList(): Flow<List<PokemonDetails>> {
        val outputList = mutableListOf<PokemonDetails>()

        return flow {
            pokeDao.getAllPokemonLocal().collect { pokemonLocalList ->
                pokemonLocalList.map { pokemonLocal ->
                    val stats = statDao.getStatsLocal(pokemonLocal.name).map { statLocal ->
                        mapper.mapStatLocalToStat(statLocal)
                    }
                    val types = typeDao.getTypesLocal(pokemonLocal.name).map { typeLocal ->
                        mapper.mapTypeLocalToType(typeLocal)
                    }
                    outputList.add(
                        PokemonDetails(
                            height = pokemonLocal.height,
                            id = pokemonLocal.id,
                            name = pokemonLocal.name,
                            weight = pokemonLocal.weight,
                            stats = stats,
                            types = types
                        )
                    )
                }
            }
            emit(outputList)
        }
    }

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

    suspend fun deletePokemon(pokemonName: String) =
        pokemonName.let {
            pokeDao.deletePokemonLocal(it)
            statDao.deleteStatLocal(it)
            typeDao.deleteTypeLocal(it)
        }

}
