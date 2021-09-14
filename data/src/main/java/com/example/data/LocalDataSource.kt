package com.example.data

import androidx.room.withTransaction
import com.example.data.database.daos.PokemonLocalDao
import com.example.data.database.daos.StatLocalDao
import com.example.data.database.daos.TypeLocalDao
import com.example.data.database.db.PokemonDatabase
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.mappers.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class LocalDataSource(
    private val database: PokemonDatabase,
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
        return flow {
            pokeDao.getAllPokemonLocal().collect { pokemonLocalList ->
                emit(pokemonLocalList.map { pokemonLocal ->
                    mapper.mapPokemonLocalToPokemonDetails(
                        pokemon = pokemonLocal,
                        stats = statDao.getStatsLocal(pokemonLocal.name),
                        types = typeDao.getTypesLocal(pokemonLocal.name)
                    )
                })
            }
        }
    }

    suspend fun insertPokemon(pokemon: PokemonDetails) =
        database.withTransaction {
            mapper.mapPokemonDetailsToPokemonLocal(pokemon).let {
                pokeDao.insertPokemonLocal(it.first)
                for (stat in it.second) {
                    statDao.insertStatLocal(stat)
                }
                for (type in it.third) {
                    typeDao.insertTypeLocal(type)
                }
            }
        }


    suspend fun deletePokemon(pokemonName: String) =
        database.withTransaction {
            pokemonName.let {
                pokeDao.deletePokemonLocal(it)
                statDao.deleteStatLocal(it)
                typeDao.deleteTypeLocal(it)
            }
        }


}
